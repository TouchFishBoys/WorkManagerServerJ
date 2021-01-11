package com.my.workmanagement.service;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.CourseSelectionDO;
import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.entity.upk.CourseSelectionUPK;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.UnsupportedFileTypeException;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.repository.*;
import com.my.workmanagement.service.interfaces.StudentService;
import com.my.workmanagement.util.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final CourseSelectionRepository courseSelectionRepository;
    private final NormalWorkRepository normalWorkRepository;
    private final TopicRepository topicRepository;
    private final TeamRepository teamRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    StudentServiceImpl(
            StudentRepository studentRepository,
            CourseSelectionRepository courseSelectionRepository,
            NormalWorkRepository normalWorkRepository,
            TopicRepository topicRepository,
            TeamRepository teamRepository,
            CourseRepository courseRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.studentRepository = studentRepository;
        this.courseSelectionRepository = courseSelectionRepository;
        this.normalWorkRepository = normalWorkRepository;
        this.topicRepository = topicRepository;
        this.teamRepository = teamRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Resource getStudentExcel(String courseId) {
        return null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean importStudents(Integer courseId, MultipartFile file, String defaultPassword)
            throws UnsupportedFileTypeException, IOException, IdNotFoundException {
        List<StudentDO> students = ExcelUtils.readFromExcel(file);
        CourseDO course = courseRepository.findByCourseId(courseId);
        List<CourseSelectionDO> courseSelectionList = new LinkedList<>();
        if (course == null) {
            throw new IdNotFoundException("course id");
        }
        String generatedPassword = passwordEncoder.encode(defaultPassword);
        logger.debug("Generated password:{}", generatedPassword);
        for (StudentDO student : students) {
            student.setStudentPassword(generatedPassword);

        }
        studentRepository.saveAll(students).forEach(student -> {
            courseSelectionRepository.insert(course.getCourseId(), student.getStudentId());
        });
        return true;
    }

    @Override
    public StudentInfoBO getStudentInfo(Integer studentId) throws IdNotFoundException {
        StudentDO studentDO = studentRepository.findByStudentId(studentId);
        if (studentDO == null) {
            throw new IdNotFoundException("studentId");
        }
        StudentInfoBO studentInfoBO = new StudentInfoBO();
        studentInfoBO.setStudentNum(studentDO.getStudentNum());
        studentInfoBO.setStudentName(studentDO.getStudentName());
        studentInfoBO.setStudentClass(studentDO.getStudentClass());
        return studentInfoBO;
    }

    @Override
    public List<CourseInfoBO> getSelectedCourseInfo(Integer studentId) throws IdNotFoundException {
        List<CourseInfoBO> list = new LinkedList<>();
        if (!studentRepository.existsByStudentId(studentId)) {
            throw new IdNotFoundException("studentId");
        }
        StudentDO tmpStudent = studentRepository.findByStudentId(studentId);

        List<CourseSelectionDO> courseSelections = courseSelectionRepository.findAllByStudent(tmpStudent);
        logger.info("Found {} course selected by {}", courseSelections.size(), studentId);
        for (CourseSelectionDO courseSelection : courseSelections) {
            CourseDO course = courseSelection.getCourse();
            Integer studentCount = courseSelectionRepository.countAllByCourse(course);
            // TODO: 2021/1/4 加上缺少的值
            list.add(CourseInfoBO.CourseInfoBOBuilder.aCourseInfoBO()
                    .withCourseName(course.getCourseName())
                    .withCourseId(course.getCourseId())
                    .withCourseTeacherName(course.getTeacher().getTeacherName())
                    .withCourseYear(course.getCourseYear())
                    .withFinishCount(normalWorkRepository.countAllByStudent(tmpStudent))
                    .withTotalCount(topicRepository.countAllByCourse_CourseId(course.getCourseId()))
                    .withCourseDescription(course.getCourseDescription())
                    .withStudentCount(studentCount)
                    .build()
            );
        }
        return list;
    }

    @Override
    public List<StudentInfoBO> getStudentsByTeamId(Integer teamId) throws IdNotFoundException {
        List<CourseSelectionDO> courseSelectionDOS = courseSelectionRepository.getAllByTeam_TeamId(teamId);

        List<StudentInfoBO> result = new LinkedList<>();
        for (CourseSelectionDO courseSelection : courseSelectionDOS) {
            StudentInfoBO info = new StudentInfoBO();
            StudentDO student = studentRepository.findByStudentId(courseSelection.getStudent().getStudentId());

            info.setStudentId(student.getStudentId());
            info.setStudentName(student.getStudentName());
            info.setStudentNum(student.getStudentNum());
            info.setStudentClass(student.getStudentClass());

            result.add(info);
        }
        return result;
    }


}
