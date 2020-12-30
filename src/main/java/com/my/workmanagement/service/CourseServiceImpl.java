package com.my.workmanagement.service;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.TeacherDO;
import com.my.workmanagement.entity.TopicDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.TopicInfoBO;
import com.my.workmanagement.payload.response.CourseInfoResponse;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import com.my.workmanagement.repository.CourseRepository;
import com.my.workmanagement.repository.NormalWorkRepository;
import com.my.workmanagement.repository.TeacherRepository;
import com.my.workmanagement.repository.TopicRepository;
import com.my.workmanagement.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final TopicRepository topicRepository;
    private final NormalWorkRepository normalWorkRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, TopicRepository topicRepository,NormalWorkRepository normalWorkRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.topicRepository = topicRepository;
        this.normalWorkRepository=normalWorkRepository;
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
    public List<TopicInfoBO> getTopicInfoByCourseId(Integer courseId) throws IdNotFoundException {
        List<TopicDO> topics = topicRepository.findAllByCourseId(courseId);
        List<TopicInfoBO> topicInfoBOS = new LinkedList<>();
        if (topics == null) {
            throw new IdNotFoundException("topic");
        }
        for (TopicDO topic : topics) {
            topicInfoBOS.add(TopicInfoBO.TopicInfoBOBuilder.aTopicInfoBO()
                    .withTopicId(topic.getTopicId())
                    .withTopicName(topic.getTopicName())
                    .withTopicDescription(topic.getTopicDescription())
                    .withFinishedCount(normalWorkRepository.countAllByTopic_CourseId(courseId))
                    .withTotalCount(topicRepository.countAllByCourseId(courseId))
                    .withStartTime(topic.getTopicTimeStart())
                    .withFinishTime(topic.getTopicTimeEnd())
                    .build());
        }
        return topicInfoBOS;
    }
}
