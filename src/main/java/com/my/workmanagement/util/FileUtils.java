package com.my.workmanagement.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static boolean save(MultipartFile file, String path) {
        try {
            byte[] bytes = file.getBytes();
            Path path1 = Paths.get(path);
            Files.write(path1, bytes);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }
        return true;
    }
}
