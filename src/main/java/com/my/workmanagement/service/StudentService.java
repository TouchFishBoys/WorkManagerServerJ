package com.my.workmanagement.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    Resource getStudentExcel(String courseId);

    boolean importStudents(MultipartFile file);
}
