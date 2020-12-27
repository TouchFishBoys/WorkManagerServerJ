package com.my.workmanagement.service;

import com.my.workmanagement.entity.TopicDO;
import com.my.workmanagement.payload.response.normalWrok.TopicInfoResponse;
import com.my.workmanagement.repository.TopicRepository;
import com.my.workmanagement.service.interfaces.NormalWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class NormalWorkServiceImpl implements NormalWorkService {
    private final TopicRepository topicRepository;

    @Autowired
    public NormalWorkServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
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
    public TopicDO getTopicInfo(Integer topicId){
        return topicRepository.findByTopicId(topicId);
    }


}
