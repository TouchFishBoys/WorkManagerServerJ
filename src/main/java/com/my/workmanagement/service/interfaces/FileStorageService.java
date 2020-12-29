package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    boolean store(MultipartFile file);
    boolean load();
    Resource loadAsResource(String path) throws StorageFileNotFoundException;
}
