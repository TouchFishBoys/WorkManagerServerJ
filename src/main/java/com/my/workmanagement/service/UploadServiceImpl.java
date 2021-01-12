package com.my.workmanagement.service;

import com.my.workmanagement.config.StorageConfiguration;
import com.my.workmanagement.model.UploadInfo;
import com.my.workmanagement.repository.CourseSelectionRepository;
import com.my.workmanagement.repository.StudentRepository;
import com.my.workmanagement.repository.TopicRepository;
import com.my.workmanagement.service.interfaces.FileStorageService;
import com.my.workmanagement.service.interfaces.UploadService;
import com.my.workmanagement.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {
    private final FileStorageService fileStorageService;
    private final TopicRepository topicRepository;
    private final StudentRepository studentRepository;

    UploadServiceImpl(
            TopicRepository topicRepository,
            StudentRepository studentRepository,
            FileStorageService fileStorageService
    ) {
        this.topicRepository = topicRepository;
        this.studentRepository = studentRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public UploadInfo uploadDocument(MultipartFile file, String uploadFilePath, Integer courseId, Integer teamId) throws Exception {
        UploadInfo uploadInfo = new UploadInfo();
        String location = courseId + "/final/" + teamId + "/" + "document.docx";

        boolean exists = fileStorageService.exists(location + "/document.docx");
        boolean success = fileStorageService.store(file, location, "document.docx");

        uploadInfo.setSuccess(success);
        uploadInfo.setExists(exists);
        return uploadInfo;
    }

    @Override
    public UploadInfo uploadFinalWorkFile(MultipartFile file, String uploadFilePath, Integer courseId, Integer teamId) throws Exception {
        if (file.isEmpty())
            return null;
        UploadInfo uploadInfo = new UploadInfo();
        String location = courseId + "/final/" + teamId;

        uploadInfo.setExists(fileStorageService.exists(location + "/file.war"));
        uploadInfo.setSuccess(fileStorageService.store(file, location, "file.war"));

        return uploadInfo;
    }

    @Override
    public UploadInfo uploadNormalWorkFile(MultipartFile file, Integer topicId, Integer stuId) throws IOException {
        if (file.isEmpty())
            return null;
        UploadInfo uploadInfo = new UploadInfo();

        Integer courseId = topicRepository.findByTopicId(topicId).getCourse().getCourseId();

        String location = courseId + "/normal/" + topicId;
        String fileName = studentRepository.findByStudentId(stuId).getStudentNum() + ".zip";
        boolean isExists = fileStorageService.store(file, location, fileName);

        return uploadInfo;
    }
}
