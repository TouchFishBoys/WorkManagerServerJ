package com.my.workmanagement.service;

import com.my.workmanagement.service.interfaces.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Override
    public boolean store(MultipartFile file) {

        return false;
    }

    @Override
    public boolean load() {
        return false;
    }
}
