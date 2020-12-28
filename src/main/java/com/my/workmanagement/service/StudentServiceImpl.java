package com.my.workmanagement.service;

import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.payload.response.student.StudentInfoResponse;
import com.my.workmanagement.repository.StudentRepository;
import com.my.workmanagement.service.interfaces.AuthService;
import com.my.workmanagement.service.interfaces.StudentService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Resource getStudentExcel(String courseId) {
        return null;
    }

    @Override
    public boolean importStudents(Integer courseId, MultipartFile file) {
        return false;
    }

}
