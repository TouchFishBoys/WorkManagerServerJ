package com.my.workmanagement.service;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.CourseSelectionDO;
import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.payload.response.student.StudentInfoResponse;
import com.my.workmanagement.repository.*;
import com.my.workmanagement.service.interfaces.AuthService;
import com.my.workmanagement.service.interfaces.StudentService;
import org.hibernate.hql.internal.ast.util.TokenPrinters;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseSelectionRepository courseSelectionRepository;
    private final NormalWorkRepository normalWorkRepository;
    private final TopicRepository topicRepository;
    private final TeamRepository teamRepository;

    StudentServiceImpl(
            StudentRepository studentRepository,
            CourseSelectionRepository courseSelectionRepository,
            NormalWorkRepository normalWorkRepository,
            TopicRepository topicRepository,
            TeamRepository teamRepository
    ) {
        this.studentRepository = studentRepository;
        this.courseSelectionRepository = courseSelectionRepository;
        this.normalWorkRepository = normalWorkRepository;
        this.topicRepository = topicRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Resource getStudentExcel(String courseId) {
        return null;
    }

    @Override
    public boolean importStudents(Integer courseId, MultipartFile file) {
        return false;
    }

    @Override
    public StudentInfoBO getStudentInfo(Integer studentId) throws IdNotFoundException {
        StudentDO studentDO = studentRepository.findByStudentId(studentId);
        if(studentDO==null) {
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
        if (studentRepository.existsByStudentId(studentId)) {
            throw new IdNotFoundException("studentId");
        }
        //StudentDO tmpStudent = new StudentDO(studentId);
        StudentDO tmpStudent = studentRepository.findByStudentId(studentId);
        List<CourseDO> courses = courseSelectionRepository.findAllByStudent(tmpStudent);
        for (CourseDO course : courses) {
            list.add(CourseInfoBO.CourseInfoBOBuilder.aCourseInfoBO()
                    .withCourseName(course.getCourseName())
                    .withCourseId(course.getCourseId())
                    .withCourseTeacherName(course.getTeacher().getTeacherName())
                    .withFinishCount(normalWorkRepository.countAllByStudent(tmpStudent))
                    .withTotalCount(topicRepository.countAllByCourseId(course.getCourseId()))
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
