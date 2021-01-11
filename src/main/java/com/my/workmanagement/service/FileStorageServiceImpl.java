package com.my.workmanagement.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.my.workmanagement.config.OSSConfig;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.service.interfaces.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private static final Logger logger = LoggerFactory.getLogger(FileStorageServiceImpl.class);
    private final OSSConfig ossConfig;
    private final OSS ossClient;

    @Autowired
    FileStorageServiceImpl(OSS ossClient, OSSConfig ossConfig) {
        this.ossClient = ossClient;
        this.ossConfig = ossConfig;
    }

    @Override
    public boolean store(MultipartFile file, String path, String fileName) {
        try {
            return store(file.getInputStream(), path, fileName, file.getContentType());
        } catch (IOException e) {
            logger.error("Error occurred: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean store(byte[] content, String path, String fileName, String contentType) {
        try (InputStream inputStream = new ByteArrayInputStream(content)) {
            store(inputStream, path, fileName, contentType);
        } catch (IOException ex) {
            logger.error("Error occurred: {}", ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean store(InputStream inputStream, String path, String fileName, String contentType) {
        try {
            logger.info("上传到OSS: fileName=\"{}\" contentLength={}", fileName, inputStream.available());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(contentType);
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            String location = path + "/" + fileName;
            // 上传文件
            ossClient.putObject(ossConfig.getBucketName(), location, inputStream, objectMetadata);
        } catch (IOException e) {
            logger.error("Error occurred: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public Resource loadAsResource(String path) throws StorageFileNotFoundException {
        if (ossClient.doesObjectExist(ossConfig.getBucketName(), path)) {
            throw new StorageFileNotFoundException();
        }
        OSSObject ossObject = ossClient.getObject(ossConfig.getBucketName(), path);

        return new InputStreamResource(ossObject.getObjectContent());
    }
}
