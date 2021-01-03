package com.my.workmanagement.service;

import com.my.workmanagement.entity.NormalWorkDO;
import com.my.workmanagement.entity.TopicDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.TopicInfoBO;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import com.my.workmanagement.repository.CourseRepository;
import com.my.workmanagement.repository.NormalWorkRepository;
import com.my.workmanagement.repository.TopicRepository;
import com.my.workmanagement.service.interfaces.NormalWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class NormalWorkServiceImpl implements NormalWorkService {
    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;
    private final NormalWorkRepository normalWorkRepository;

    @Autowired
    public NormalWorkServiceImpl(
            TopicRepository topicRepository,
            CourseRepository courseRepository,
            NormalWorkRepository normalWorkRepository
    ) {
        this.topicRepository = topicRepository;
        this.courseRepository = courseRepository;
        this.normalWorkRepository = normalWorkRepository;
    }

    @Override
    public boolean store(Integer stuId, Integer topicId, MultipartFile file) {
        return false;
    }

    @Override
    public Resource loadResource(Integer stuId, Integer topicId) {
        return null;
    }

    @Override
    public List<String> getStuSubmittedList(Integer stuId) {
        return null;
    }

    @Override
    public List<String> getTopicSubmittedList(Integer topicId) {
        return null;
    }

    @Override
    public TopicInfoResponse getTopicInfo(Integer topicId) throws IdNotFoundException {
        TopicDO topicDemo = topicRepository.findByTopicId(topicId);
        if (topicDemo == null) {
            throw new IdNotFoundException("topicid");
        }
        return TopicInfoResponse.TopicInfoResponseBuilder.aTopicInfoResponse()
                .withCourseName(courseRepository.findByCourseId(topicId).getCourseName())
                .withTopicName(topicDemo.getTopicName())
                .withTopicDescription(topicDemo.getTopicDescription())
                .withTopicTimeStart(topicDemo.getTopicTimeStart())
                .withTopicTimeEnd(topicDemo.getTopicTimeEnd()).build();
    }

    @Override
    @Transactional
    public Integer createTopic(String topicName, String topicDescription, Integer courseId, Date startTime, Date finishTime) {
        return null;
    }

    @Override
    public List<TopicInfoBO> getTopicInfosAsStudent(Integer courseId, Integer studentId) throws IdNotFoundException {
        return null;
    }

    @Override
    public List<TopicInfoBO> getTopicInfosAsTeacher(Integer courseId, Integer teacherId) throws IdNotFoundException {
        return null;
    }

    @Override
    @Transactional
    public boolean setScore(Integer topicId, Integer studentId, Integer score) throws IdNotFoundException {
        NormalWorkDO normalWork = normalWorkRepository.getFirstByTopic_TopicIdAndStudent_StudentId(topicId, studentId);
        if (normalWork == null) {
            throw new IdNotFoundException("normalwork");
        }
        return normalWorkRepository.setScoreById(normalWork.getNworkId(), score);
    }


}
