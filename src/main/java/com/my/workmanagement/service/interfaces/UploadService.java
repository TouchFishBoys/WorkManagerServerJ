package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.model.UploadInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    UploadInfo uploadDocument(MultipartFile file, String uploadFilePath, Integer courseId, Integer teamId) throws Exception;

    UploadInfo uploadFinalWorkFile(MultipartFile file, String uploadFilePath, Integer courseId, Integer teamId) throws Exception;

    UploadInfo uploadNormalWorkFile(MultipartFile file, Integer topicId, Integer stuId) throws IOException;

}
