package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.payload.response.student.StudentInfoResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    Resource getStudentExcel(String courseId);

    boolean importStudents(Integer courseId, MultipartFile file);

}
