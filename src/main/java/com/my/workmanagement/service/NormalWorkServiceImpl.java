package com.my.workmanagement.service;

import com.my.workmanagement.config.StorageConfiguration;
import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.NormalWorkDO;
import com.my.workmanagement.entity.TopicDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.NormalWorkBO;
import com.my.workmanagement.model.bo.TopicInfoBO;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import com.my.workmanagement.repository.CourseRepository;
import com.my.workmanagement.repository.CourseSelectionRepository;
import com.my.workmanagement.repository.NormalWorkRepository;
import com.my.workmanagement.repository.TopicRepository;
import com.my.workmanagement.service.interfaces.NormalWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NormalWorkServiceImpl implements NormalWorkService {
    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;
    private final NormalWorkRepository normalWorkRepository;
    private final CourseSelectionRepository courseSelectionRepository;

    @javax.annotation.Resource
    private StorageConfiguration storageConfiguration;

    @Autowired
    public NormalWorkServiceImpl(
            TopicRepository topicRepository,
            CourseRepository courseRepository,
            NormalWorkRepository normalWorkRepository,
            CourseSelectionRepository courseSelectionRepository
    ) {
        this.topicRepository = topicRepository;
        this.courseRepository = courseRepository;
        this.normalWorkRepository = normalWorkRepository;
        this.courseSelectionRepository = courseSelectionRepository;

    }

    @Override
    public boolean store(Integer stuId, Integer topicId, MultipartFile file) throws Exception {
        Integer courseId = topicRepository.findByTopicId(topicId).getCourse().getCourseId();
        String location =
                storageConfiguration.getRootDirectory() + "/" + courseId + "/normal/" + topicId + "/" + stuId + "/" + file.getOriginalFilename();
        File outputFile = new File(location);
        outputFile.mkdirs();
        file.transferTo(outputFile);
        return true;
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
    public Integer createTopic(String topicName, String topicDescription, Integer courseId, Date startTime, Date finishTime) throws IdNotFoundException {
        TopicDO topic = new TopicDO();
        CourseDO course = courseRepository.findByCourseId(courseId);
        if (course == null) {
            throw new IdNotFoundException("course id");
        }
        topic.setCourse(course);
        topic.setTopicName(topicName);
        topic.setTopicDescription(topicDescription);
        topic.setTopicTimeStart(new Timestamp(startTime.getTime()));
        topic.setTopicTimeEnd(new Timestamp(finishTime.getTime()));
        return topicRepository.save(topic).getTopicId();
    }

    @Override
    public List<TopicInfoBO> getTopicInfos(Integer courseId) throws IdNotFoundException {
        if (!courseRepository.existsById(courseId)) {
            throw new IdNotFoundException("course id");
        }
        List<TopicDO> topics = topicRepository.findAllByCourse_CourseId(courseId);
        List<TopicInfoBO> topicInfos = new ArrayList<>();
        topics.forEach(item -> {
            topicInfos.add(getTopicBoFromDo(item));
        });
        return topicInfos;
    }

    @Override
    @Transactional
    public boolean setScore(Integer topicId, Integer studentId, Integer score) throws IdNotFoundException {
        NormalWorkDO normalWork = normalWorkRepository.getFirstByTopic_TopicIdAndStudent_StudentId(topicId, studentId);
        if (normalWork == null) {
            throw new IdNotFoundException("normalwork");
        }
        return normalWorkRepository.setScoreById(normalWork.getNworkId(), score) > 0;
    }

    private TopicInfoBO getTopicBoFromDo(TopicDO topic) {
        Integer totalCount = courseSelectionRepository.countAllByCourse_CourseId(topic.getCourse().getCourseId());
        Integer finishedCount = normalWorkRepository.countAllByTopic_TopicId(topic.getTopicId());

        return TopicInfoBO.TopicInfoBOBuilder.aTopicInfoBO()
                .withTopicId(topic.getTopicId())
                .withTopicName(topic.getTopicName())
                .withTopicDescription(topic.getTopicDescription())
                .withTotalCount(totalCount)
                .withFinishedCount(finishedCount)
                .withStartTime(topic.getTopicTimeStart())
                .withFinishTime(topic.getTopicTimeEnd())
                .build();
    }

    @Override
    public List<NormalWorkBO> getFinishedNormalWork_Topic(Integer topicId) throws IdNotFoundException {
        List<NormalWorkBO> list = getNormalWork_Topic(topicId);
        list.removeIf(normalWorkBO -> normalWorkBO.getTimeUpload()==null);
        return list;

    }

    @Override
    public List<NormalWorkBO> getFinishedNormalWork_Student(Integer studentId) throws IdNotFoundException {
        List<NormalWorkBO> list = getNormalWork_Student(studentId);
        list.removeIf(normalWorkBO -> normalWorkBO.getTimeUpload() == null);
        return list;

    }

    @Override
    public List<NormalWorkBO> getNormalWork_Topic(Integer topicId) throws IdNotFoundException {
        List<NormalWorkDO> normalWorkDOS = normalWorkRepository.findAllByTopic_TopicId(topicId);
        if(normalWorkDOS==null) {
            throw new IdNotFoundException("topicId="+topicId.toString());
        }
        return getNormalWorkBOS(normalWorkDOS);
    }

    @Override
    public List<NormalWorkBO> getNormalWork_Student(Integer studentId) throws IdNotFoundException {
        List<NormalWorkDO> normalWorkDOS = normalWorkRepository.findAllByStudent_StudentId(studentId);
        if(normalWorkDOS==null) {
            throw new IdNotFoundException("studentId="+studentId.toString());
        }
        return getNormalWorkBOS(normalWorkDOS);
    }

    private List<NormalWorkBO> getNormalWorkBOS(List<NormalWorkDO> normalWorkDOS) {
        List<NormalWorkBO> list = new ArrayList<>();
        for (NormalWorkDO normalWorkDO : normalWorkDOS) {
            list.add(NormalWorkBO.NormalWorkBOBuilder.aNormalWorkBO()
                    .withNworkName(normalWorkDO.getNworkName())
                    .withNworkScore(normalWorkDO.getNworkScore())
                    .withNworkId(normalWorkDO.getNworkId())
                    .withTimeUpload(normalWorkDO.getTimeUpload())
                    .withTopic(normalWorkDO.getTopic())
                    .withStudent(normalWorkDO.getStudent())
                    .build());
        }
        return list;
    }

}
