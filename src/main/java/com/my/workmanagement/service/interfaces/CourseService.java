package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.payload.response.CourseInfoResponse;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    boolean createCourse(String courseName, String teacherNum, Integer[] studentNums);
    CourseInfoResponse getCourseInfo(Integer courseId)throws IdNotFoundException;
    String getCourseName(Integer courseId) throws IdNotFoundException;
}
