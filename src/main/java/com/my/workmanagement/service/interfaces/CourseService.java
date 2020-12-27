package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;

public interface CourseService {
    boolean createCourse(String courseName, String teacherNum, Integer[] studentNums);

    String getCourseName(Integer courseId) throws IdNotFoundException;
}
