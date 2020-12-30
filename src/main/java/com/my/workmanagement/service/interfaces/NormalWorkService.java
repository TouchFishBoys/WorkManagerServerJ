package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.TopicInfoBO;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@Service
public interface NormalWorkService {
    boolean store(Integer stuId, Integer topicId, MultipartFile file);

    Resource loadResource(Integer stuId, Integer topicId);

    List<String> getStuSubmittedList(Integer stuId);

    List<String> getTopicSubmittedList(Integer topicId);

    TopicInfoResponse getTopicInfo(Integer topicId);

    Integer createTopic(String topicName, String topicDescription, Integer courseId, Date startTime, Date finishTime);

    List<TopicInfoBO> getTopicInfosAsStudent(Integer courseId, Integer studentId) throws IdNotFoundException;

    List<TopicInfoBO> getTopicInfosAsTeacher(Integer courseId, Integer teacherId) throws IdNotFoundException;

}
