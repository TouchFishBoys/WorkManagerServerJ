package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.TopicInfoBO;
import com.my.workmanagement.payload.response.CourseInfoResponse;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    boolean createCourse(String courseName, String teacherNum, Integer[] studentNums);

    CourseInfoBO getCourseInfo_Student(Integer courseId, Integer studentId) throws IdNotFoundException;

    CourseInfoBO getCourseInfo_Teacher(Integer courseId) throws IdNotFoundException;

    String getCourseName(Integer courseId) throws IdNotFoundException;

    List<TopicInfoBO> getTopicInfoByCourseId(Integer courseId) throws IdNotFoundException;
}
