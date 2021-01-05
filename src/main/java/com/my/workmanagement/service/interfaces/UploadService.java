package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.model.UploadInfo;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    UploadInfo uploadDocument(MultipartFile file, String uploadFilePath,Integer courseId,Integer teamId) throws Exception;
    UploadInfo uploadFile(MultipartFile file, String uploadFilePath,Integer courseId,Integer teamId) throws Exception;
}
