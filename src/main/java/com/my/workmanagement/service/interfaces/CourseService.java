package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.payload.response.CourseInfoResponse;
import com.my.workmanagement.payload.response.normalWork.TopicInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    boolean createCourse(String courseName, String teacherNum, Integer[] studentNums);

    CourseInfoResponse getCourseInfo(Integer courseId) throws IdNotFoundException;

    String getCourseName(Integer courseId) throws IdNotFoundException;

    List<TopicInfoResponse> getTopicResponses(Integer courseId) throws IdNotFoundException;
}
