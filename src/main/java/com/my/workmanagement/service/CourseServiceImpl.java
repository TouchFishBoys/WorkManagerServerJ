package com.my.workmanagement.service;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.TeacherDO;
import com.my.workmanagement.entity.TopicDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.model.bo.TopicInfoBO;
import com.my.workmanagement.payload.response.CourseInfoResponse;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import com.my.workmanagement.repository.*;
import com.my.workmanagement.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseSelectionRepository courseSelectionRepository;
    private final TopicRepository topicRepository;
    private final NormalWorkRepository normalWorkRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, TopicRepository topicRepository, NormalWorkRepository normalWorkRepository,CourseSelectionRepository courseSelectionRepository) {
        this.courseRepository = courseRepository;
        this.courseSelectionRepository=courseSelectionRepository;
        this.topicRepository = topicRepository;
        this.normalWorkRepository = normalWorkRepository;
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
    public CourseInfoBO getCourseInfo_Teacher(Integer courseId) throws IdNotFoundException {
        CourseDO courseDO = courseRepository.findByCourseId(courseId);
        if (courseDO == null) {
            throw new IdNotFoundException("course");
        }
        return CourseInfoBO.CourseInfoBOBuilder.aCourseInfoBO()
                .withCourseId(courseDO.getCourseId())
                .withCourseName(courseDO.getCourseName())
                .withCourseDescription(courseDO.getCourseDescription())
                .withCourseYear(courseDO.getCourseYear())
                .withCourseTeacherName(courseDO.getTeacher().getTeacherName())
                .withStudentCount(courseSelectionRepository.countAllByCourse_CourseId(courseId))
                //.withTotalCount(courseSelectionRepository.countAllByCourseIdGroupByTeam(courseId)) //SQL问题
                .withFinishCount(courseSelectionRepository.countAllByCourse_CourseIdAndTeam_FinalWork_TimeUploadNot(courseId,null))
                .build();
    }

    @Override
    public CourseInfoBO getCourseInfo_Student(Integer courseId, Integer studentId) throws IdNotFoundException {
        CourseDO courseDO = courseRepository.findByCourseId(courseId);
        if (courseDO == null) {
            throw new IdNotFoundException("course");
        }
        return CourseInfoBO.CourseInfoBOBuilder.aCourseInfoBO()
                .withCourseId(courseDO.getCourseId())
                .withCourseName(courseDO.getCourseName())
                .withCourseDescription(courseDO.getCourseDescription())
                .withCourseYear(courseDO.getCourseYear())
                .withStudentCount(1)
                .withCourseTeacherName(courseDO.getTeacher().getTeacherName())
                .withTotalCount(normalWorkRepository.countAllByTopic_CourseIdAndStudent_StudentId(courseId,studentId))
                .withFinishCount(normalWorkRepository.countAllByTopic_CourseId(courseId))
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
    @Override
    public List<StudentInfoBO> getStudentInfo(Integer courseId) throws  IdNotFoundException{

        return null;
    }
}
