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
        StudentDO studentDO = studentRepository.findByStudentId(studentId);
        StudentInfoBO studentInfoBO = new StudentInfoBO();
        studentInfoBO.setStudentNum(studentDO.getStudentNum());
        studentInfoBO.setStudentName(studentDO.getStudentName());
        studentInfoBO.setStudentId(studentId);
        studentInfoBO.setStudentClass(studentDO.getStudentClass());
        return studentInfoBO;
    }

    @Override
    public List<CourseInfoBO> getCourseSelectionInfo(Integer studentId) throws IdNotFoundException {
        List<CourseInfoBO> list = null;
        StudentDO studentDO = studentRepository.findByStudentId(studentId);
        List<CourseDO> courseDOS = courseSelectionRepository.findAllByStudent(studentDO);
        for (int i = 0; i < courseDOS.size(); i++) {
            list.add(CourseInfoBO.CourseInfoBOBuilder.aCourseInfoBOBuilder()
                    .withCourseName(courseDOS.get(i).getCourseName())
                    .withCourseId(courseDOS.get(i).getCourseId())
                    .withCourseTeacherName(courseDOS.get(i).getTeacher().getTeacherName())
                    .withFinishCount(normalWorkRepository.countAllByStudent(studentDO))
                    .withTotalCount(topicRepository.countAllByCourseId(courseDOS.get(i).getCourseId()))
                    .build()
            );
        }
        return list;
    }

}
