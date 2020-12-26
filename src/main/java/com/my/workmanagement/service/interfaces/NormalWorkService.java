package com.my.workmanagement.service.interfaces;

import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface NormalWorkService {
    boolean store(Integer stuId, Integer topicId, MultipartFile file);
    Resource loadResource(Integer stuId, Integer topicId);
    List<String> getStuSubmittedList(Integer stuId);
    List<String> getTopicSubmittedList(Integer topicId);
}
