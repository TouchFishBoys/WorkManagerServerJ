package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.FinalWorkBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.model.bo.TopicInfoBO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    Integer createCourse(Integer teacherId, String courseName, String courseDescription) throws IdNotFoundException;

    CourseInfoBO getCourseInfo_Student(Integer courseId, Integer studentId) throws IdNotFoundException;

    CourseInfoBO getCourseInfo_Teacher(Integer courseId) throws IdNotFoundException;

    String getCourseName(Integer courseId) throws IdNotFoundException;

    List<TopicInfoBO> getTopicInfoByCourseId(Integer courseId) throws IdNotFoundException;

    List<StudentInfoBO> getStudentInfo(Integer courseId) throws IdNotFoundException;

    List<FinalWorkBO> getFinalWorkList(Integer courseId) throws IdNotFoundException;
}
