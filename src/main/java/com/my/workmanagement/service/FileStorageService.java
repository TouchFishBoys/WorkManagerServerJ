package com.my.workmanagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    boolean store(MultipartFile file);
    boolean load();
}
