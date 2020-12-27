package com.my.workmanagement.service;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.repository.CourseRepository;
import com.my.workmanagement.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean createCourse(String courseName, String teacherNum, Integer[] studentNums) {
        return false;
    }

    @Override
    public String getCourseName(Integer courseId) throws IdNotFoundException {
        CourseDO course = courseRepository.findByCourseId(courseId);
        if (course == null) {
            throw new IdNotFoundException("courseName");
        }
        return course.getCourseName();
    }
}
