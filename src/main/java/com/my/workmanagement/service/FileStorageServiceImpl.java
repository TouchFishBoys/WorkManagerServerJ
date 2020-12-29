package com.my.workmanagement.service;

import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.service.interfaces.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Value("${workmanager.storage.root-directory:/storage}")
    private String storageRoot;

    @Override
    public boolean store(MultipartFile file) {

        return false;
    }

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public Resource loadAsResource(String path) throws StorageFileNotFoundException {
        File file = new File(storageRoot + path);
        if (!file.exists() || !file.isFile()) {
            throw new StorageFileNotFoundException();
        }
        try {
            return new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            throw new StorageFileNotFoundException();
        }
    }
}
