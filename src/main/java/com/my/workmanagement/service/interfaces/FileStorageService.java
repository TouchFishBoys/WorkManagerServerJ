package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileStorageService {
    boolean store(MultipartFile file, String path, String fileName) throws IOException;

    boolean store(InputStream inputStream, String path, String fileName, String contentType) throws IOException;

    boolean store(byte[] content, String path, String fileName, String contentType) throws IOException;

    boolean exists(String file);

    Resource loadAsResource(String path) throws StorageFileNotFoundException;
}
