package com.my.workmanagement.service;

import com.my.workmanagement.config.StorageConfiguration;
import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.NormalWorkDO;
import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.entity.TopicDO;
import com.my.workmanagement.exception.DataConflictException;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.exception.StorageIOException;
import com.my.workmanagement.model.bo.NormalWorkBO;
import com.my.workmanagement.model.bo.TopicInfoBO;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import com.my.workmanagement.repository.*;
import com.my.workmanagement.service.interfaces.FileStorageService;
import com.my.workmanagement.service.interfaces.NormalWorkService;
import com.my.workmanagement.service.interfaces.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NormalWorkServiceImpl implements NormalWorkService {
    private final FileStorageService fileStorageService;
    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;
    private final NormalWorkRepository normalWorkRepository;
    private final CourseSelectionRepository courseSelectionRepository;
    private final StudentRepository studentRepository;
    private final UploadService uploadService;

    @javax.annotation.Resource
    private StorageConfiguration storageConfiguration;

    @Autowired
    public NormalWorkServiceImpl(
            StudentRepository studentRepository,
            TopicRepository topicRepository,
            CourseRepository courseRepository,
            NormalWorkRepository normalWorkRepository,
            CourseSelectionRepository courseSelectionRepository,
            FileStorageService fileStorageService,
            UploadService uploadService
    ) {
        this.studentRepository = studentRepository;
        this.topicRepository = topicRepository;
        this.courseRepository = courseRepository;
        this.normalWorkRepository = normalWorkRepository;
        this.courseSelectionRepository = courseSelectionRepository;
        this.fileStorageService = fileStorageService;
        this.uploadService = uploadService;
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
        list.removeIf(normalWorkBO -> normalWorkBO.getTimeUpload() == null);
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
        if (normalWorkDOS == null) {
            throw new IdNotFoundException("topicId=" + topicId.toString());
        }
        return getNormalWorkBOS(normalWorkDOS);
    }

    @Override
    public List<NormalWorkBO> getNormalWork_Student(Integer studentId) throws IdNotFoundException {
        List<NormalWorkDO> normalWorkDOS = normalWorkRepository.findAllByStudent_StudentId(studentId);
        if (normalWorkDOS == null) {
            throw new IdNotFoundException("studentId=" + studentId.toString());
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

    @Override
    public Resource getNormalWorkFile(Integer stuId, Integer topicId) throws IdNotFoundException, StorageFileNotFoundException {
        StudentDO student = studentRepository.findByStudentId(stuId);
        if (student == null) {
            throw new IdNotFoundException("student id");
        }
        TopicDO topic = topicRepository.findByTopicId(topicId);
        if (topic == null) {
            throw new IdNotFoundException("topic id");
        }
        Integer courseId = topic.getCourse().getCourseId();
        String location = courseId + "/normal/" + topicId + "/" + student.getStudentNum() + ".zip";
        return fileStorageService.loadAsResource(location);
    }

    @Transactional(noRollbackFor = {IdNotFoundException.class}, rollbackFor = {IOException.class})
    public boolean submit(Integer topicId, Integer studentId, MultipartFile file)
            throws IdNotFoundException, StorageIOException, DataConflictException {
        TopicDO topic = topicRepository.findByTopicId(topicId);
        if (topic == null) {
            throw new IdNotFoundException("topic id");
        }
        StudentDO student = studentRepository.findByStudentId(studentId);
        if (student == null) {
            throw new IdNotFoundException("student id");
        }
        if (normalWorkRepository.existsByTopic_TopicIdAndStudent_StudentId(topicId, studentId)) {
            throw new DataConflictException("normal-work");
        }

        NormalWorkDO normalWork = new NormalWorkDO();
        normalWork.setStudent(student);
        normalWork.setTopic(topic);
        normalWork.setTimeUpload(new Timestamp(new Date().getTime()));
        normalWorkRepository.save(normalWork);
        try {
            return uploadService.uploadNormalWorkFile(file, topicId, studentId).isSuccess();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new StorageIOException(ioException);
        }
    }
}
