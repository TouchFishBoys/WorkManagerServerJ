package com.my.workmanagement.service;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.TeacherDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.TeacherInfoBO;
import com.my.workmanagement.payload.response.student.CourseListResponse;
import com.my.workmanagement.repository.CourseRepository;
import com.my.workmanagement.repository.TeacherRepository;
import com.my.workmanagement.service.interfaces.CourseService;
import com.my.workmanagement.service.interfaces.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final CourseService courseService;

    TeacherServiceImpl(
            TeacherRepository teacherRepository,
            CourseRepository courseRepository,
            CourseService courseService
    ) {
        this.teacherRepository = teacherRepository;
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    public TeacherInfoBO getTeacherInfo(Integer teacherId) throws IdNotFoundException {
        TeacherDO teacherDO = teacherRepository.findByTeacherId(teacherId);
        if (teacherDO == null) {
            throw new IdNotFoundException("teacherId:" + teacherId.toString());
        }
        return TeacherInfoBO.TeacherInfoBOBuilder.aTeacherInfoBO()
                .withTeacherId(teacherDO.getTeacherId())
                .withTeacherName(teacherDO.getTeacherName())
                .withTeacherNum(teacherDO.getTeacherNum())
                .withTeacherTell(teacherDO.getTeacherTell())
                .build();
    }

    @Override
    public CourseListResponse getHostedCourseInfoList(Integer teacherId, Pageable page) throws IdNotFoundException {
        CourseListResponse response = new CourseListResponse();
        TeacherDO teacher = teacherRepository.findByTeacherId(teacherId);
        List<CourseInfoBO> result = new LinkedList<>();
        if (teacher == null) {
            throw new IdNotFoundException("teacher id");
        }
        Page<CourseDO> coursePage = courseRepository.findAllByTeacherOrderByCourseId(teacher, page);
        for (CourseDO courseDO : coursePage) {
            result.add(courseService.getCourseInfo_Teacher(courseDO.getCourseId()));
        }
        response.setPageCount(coursePage.getTotalPages());
        response.setCourses(result);
        return response;
    }

}
