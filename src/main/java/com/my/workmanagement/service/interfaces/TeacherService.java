package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.TeacherInfoBO;

import java.util.List;

public interface TeacherService {
    TeacherInfoBO getTeacherInfo(Integer teacherId) throws IdNotFoundException;

    List<CourseInfoBO> getHostedCourseInfoList(Integer teacherId) throws IdNotFoundException;
}
