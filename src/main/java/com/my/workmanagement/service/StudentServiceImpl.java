package com.my.workmanagement.service;

import com.my.workmanagement.entity.CourseDO;
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

    private StudentRepository studentRepository;
    private CourseSelectionRepository courseSelectionRepository;
    private NormalWorkRepository normalWorkRepository;
    private TopicRepository topicRepository;

    StudentServiceImpl(
            StudentRepository studentRepository
            , CourseSelectionRepository courseSelectionRepository
            , NormalWorkRepository normalWorkRepository
            , TopicRepository topicRepository
    ) {
        this.studentRepository = studentRepository;
        this.courseSelectionRepository = courseSelectionRepository;
        this.normalWorkRepository = normalWorkRepository;
        this.topicRepository = topicRepository;
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
        StudentDO student = studentRepository.findByStudentId(studentId);
        if (student == null) {
            throw new IdNotFoundException("studentId");
        }
        StudentInfoBO studentInfoBO = new StudentInfoBO();
        studentInfoBO.setStudentNum(student.getStudentNum());
        studentInfoBO.setStudentName(student.getStudentName());
        studentInfoBO.setStudentId(studentId);
        studentInfoBO.setStudentClass(student.getStudentClass());
        return studentInfoBO;
    }

    @Override
    public List<CourseInfoBO> getSelectedCourseInfo(Integer studentId) throws IdNotFoundException {
        List<CourseInfoBO> list = new LinkedList<>();
        if (studentRepository.existsByStudentId(studentId)) {
            throw new IdNotFoundException("studentId");
        }

        StudentDO tmpStudent = new StudentDO(studentId);
        List<CourseDO> courses = courseSelectionRepository.findAllByStudent(tmpStudent);
        for (CourseDO course : courses) {
            list.add(CourseInfoBO.CourseInfoBOBuilder.aCourseInfoBOBuilder()
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
}
