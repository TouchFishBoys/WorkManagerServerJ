package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.TeacherInfoBO;
import com.my.workmanagement.payload.response.student.CourseListResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {
    TeacherInfoBO getTeacherInfo(Integer teacherId) throws IdNotFoundException;

    CourseListResponse getHostedCourseInfoList(Integer teacherId, Pageable page) throws IdNotFoundException;
}
