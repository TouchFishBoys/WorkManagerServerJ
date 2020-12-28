package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NormalWorkService {
    boolean store(Integer stuId, Integer topicId, MultipartFile file);

    Resource loadResource(Integer stuId, Integer topicId);

    List<String> getStuSubmittedList(Integer stuId);

    List<String> getTopicSubmittedList(Integer topicId);

    TopicInfoResponse getTopicInfo(Integer topicId);

    boolean createTopic(String topicName, String topicDescription, Integer courseId);
}
