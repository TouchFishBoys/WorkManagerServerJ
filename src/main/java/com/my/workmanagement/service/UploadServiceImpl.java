package com.my.workmanagement.service;

import com.my.workmanagement.config.StorageConfiguration;
import com.my.workmanagement.model.UploadInfo;
import com.my.workmanagement.service.interfaces.UploadService;
import com.my.workmanagement.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploadServiceImpl implements UploadService {

    @javax.annotation.Resource
    private StorageConfiguration storageConfiguration;
    @Override
    public UploadInfo uploadDocument(MultipartFile file, String uploadFilePath,Integer courseId,Integer teamId) throws Exception {
        if (file.isEmpty())
            return null;
        UploadInfo uploadInfo = new UploadInfo();
        String originalFilename;
        String fileName;
        originalFilename = file.getOriginalFilename();
        fileName = "document.docx";
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        long fileSize = file.getSize();
        File packageFile = new File(uploadFilePath);
       if (!packageFile.exists()) {
            packageFile.mkdir();
        }
        //File targetFile = new File(uploadFilePath + "/" + fileName);
       // file.transferTo(targetFile);
        String location =
                storageConfiguration.getRootDirectory() + "/" + courseId + "/final/" + teamId + "/"  + "document.docx";
    File targetFile = new File(location);
        targetFile.mkdirs();
        file.transferTo(targetFile);

        uploadInfo.setBeginFileName(originalFilename);
        uploadInfo.setLastFileName(fileName);
        uploadInfo.setFileType(fileType);
        uploadInfo.setFileSize(Long.toString(fileSize));
        uploadInfo.setUploadUrl(targetFile.toString());
        uploadInfo.setResult("上传成功");
        return uploadInfo;
    }

    @Override
    public UploadInfo uploadFile(MultipartFile file, String uploadFilePath,Integer courseId,Integer teamId) throws Exception {
        if (file.isEmpty())
            return null;
        UploadInfo uploadInfo = new UploadInfo();
        String originalFilename;
        String fileName;
        originalFilename = file.getOriginalFilename();
        fileName = "document.docx";
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        long fileSize = file.getSize();
        File packageFile = new File(uploadFilePath);
        if (!packageFile.exists()) {
            packageFile.mkdir();
        }
        //File targetFile = new File(uploadFilePath + "/" + fileName);
        // file.transferTo(targetFile);
        String location =
                storageConfiguration.getRootDirectory() + "/" + courseId + "/final/" + teamId + "/"  + "file.war";
        File targetFile = new File(location);
        targetFile.mkdirs();
        file.transferTo(targetFile);

        uploadInfo.setBeginFileName(originalFilename);
        uploadInfo.setLastFileName(fileName);
        uploadInfo.setFileType(fileType);
        uploadInfo.setFileSize(Long.toString(fileSize));
        uploadInfo.setUploadUrl(targetFile.toString());
        uploadInfo.setResult("上传成功");
        return uploadInfo;
    }



}
