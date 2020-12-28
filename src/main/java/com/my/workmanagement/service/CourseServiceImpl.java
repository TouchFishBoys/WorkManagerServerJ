package com.my.workmanagement.service;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.TeacherDO;
import com.my.workmanagement.entity.TopicDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.payload.response.CourseInfoResponse;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import com.my.workmanagement.repository.CourseRepository;
import com.my.workmanagement.repository.TeacherRepository;
import com.my.workmanagement.repository.TopicRepository;
import com.my.workmanagement.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, TopicRepository topicRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.topicRepository = topicRepository;
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

    @Override
    public CourseInfoResponse getCourseInfo(Integer courseId) throws IdNotFoundException {
        CourseDO courseDO = courseRepository.findByCourseId(courseId);
        if (courseDO == null) {
            throw new IdNotFoundException("course");
        }
        TeacherDO teacherDO = teacherRepository.findByTeacherId(courseDO.getTeacher().getTeacherId());
        if (teacherDO == null) {
            throw new IdNotFoundException("teacher");
        }
        return CourseInfoResponse.CourseInfoResponseBuilder.aCourseInfoResponse()
                .withCourseDescription(courseDO.getCourseDescription())
                .withCourseYear(courseDO.getCourseYear())
                .withCourseId(courseId)
                .withCourseName(courseDO.getCourseName())
                .withTeacher(teacherDO.getTeacherName())
                .withStudentCount(topicRepository.countAllByCourseId(courseId))
                .build();
    }

    @Override
    public List<TopicInfoResponse> getTopicResponses(Integer courseId) throws IdNotFoundException {
        List<TopicDO> Topics = topicRepository.findAllByCourseId(courseId);
        if(Topics==null){
            throw new IdNotFoundException("topic");
        }
        List<TopicInfoResponse> responses=null;
        for (Integer i = 0; i < Topics.size(); i++) {
            responses.add(TopicInfoResponse.TopicInfoResponseBuilder.aTopicInfoResponse()
            .withCourseName(getCourseName(courseId))
            .withTopicName(Topics.get(i).getTopicName())
            .withTopicDescription(Topics.get(i).getTopicDescription())
            .withTopicTimeStart(Topics.get(i).getTopicTimeStart())
            .withTopicTimeEnd(Topics.get(i).getTopicTimeEnd())
            .build());
        }
        return responses;
    }
}
