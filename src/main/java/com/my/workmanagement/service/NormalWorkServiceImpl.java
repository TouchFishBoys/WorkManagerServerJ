package com.my.workmanagement.service;

import com.my.workmanagement.entity.TopicDO;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import com.my.workmanagement.repository.CourseRepository;
import com.my.workmanagement.repository.TopicRepository;
import com.my.workmanagement.service.interfaces.NormalWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class NormalWorkServiceImpl implements NormalWorkService {
    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public NormalWorkServiceImpl(TopicRepository topicRepository, CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.courseRepository = courseRepository;
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
    public TopicInfoResponse getTopicInfo(Integer topicId) {
        TopicDO topicDemo = topicRepository.findByTopicId(topicId);
        return TopicInfoResponse.TopicInfoResponseBuilder.aTopicInfoResponse()
                .withCourseName(courseRepository.findByCourseId(topicId).getCourseName())
                .withTopicName(topicDemo.getTopicName())
                .withTopicDescription(topicDemo.getTopicDescription())
                .withTopicTimeStart(topicDemo.getTopicTimeStart())
                .withTopicTimeEnd(topicDemo.getTopicTimeEnd()).build();
    }

    @Override
    @Transactional
    public boolean createTopic(String topicName, String topicDescription, Integer courseId) {
        return false;
    }
}
