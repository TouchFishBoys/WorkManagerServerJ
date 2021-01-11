package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileStorageService {
    boolean store(MultipartFile file, String path, String fileName);

    boolean store(InputStream inputStream, String path, String fileName, String contentType);

    boolean store(byte[] content, String path, String fileName, String contentType);

    Resource loadAsResource(String path) throws StorageFileNotFoundException;
}
