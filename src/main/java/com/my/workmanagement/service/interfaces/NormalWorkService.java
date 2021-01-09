package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.entity.NormalWorkDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.model.bo.NormalWorkBO;
import com.my.workmanagement.model.bo.TopicInfoBO;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public interface NormalWorkService {
    boolean store(Integer stuId, Integer topicId, MultipartFile file) throws Exception;

    Resource loadResource(Integer stuId, Integer topicId) throws StorageFileNotFoundException;

    List<String> getStuSubmittedList(Integer stuId);

    TopicInfoResponse getTopicInfo(Integer topicId) throws IdNotFoundException;

    Integer createTopic(String topicName, String topicDescription, Integer courseId, Date startTime, Date finishTime) throws IdNotFoundException;

    List<TopicInfoBO> getTopicInfos(Integer courseId) throws IdNotFoundException;

    boolean setScore(Integer topicId, Integer studentId, Integer score) throws IdNotFoundException;

    List<NormalWorkBO> getNormalWork_Topic(Integer topicId) throws IdNotFoundException;

    List<NormalWorkBO> getNormalWork_Student(Integer studentId) throws IdNotFoundException;

    List<NormalWorkBO> getFinishedNormalWork_Topic(Integer topicId) throws IdNotFoundException;

    List<NormalWorkBO> getFinishedNormalWork_Student(Integer studentId) throws IdNotFoundException;

}
