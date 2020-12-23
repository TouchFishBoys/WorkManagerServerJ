package com.my.workmanagement.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class NormalWorkServiceImpl implements NormalWorkService{
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
}
