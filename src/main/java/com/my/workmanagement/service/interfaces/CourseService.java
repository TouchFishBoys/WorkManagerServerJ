package com.my.workmanagement.service.interfaces;

public interface CourseService {
    boolean createCourse(String courseName, String teacherNum, Integer[] studentNums);
}
