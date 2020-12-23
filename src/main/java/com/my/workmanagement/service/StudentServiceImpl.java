package com.my.workmanagement.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentServiceImpl implements StudentService, AuthService {
    @Override
    public Resource getStudentExcel(String courseId) {
        return null;
    }

    @Override
    public boolean importStudents(MultipartFile file) {
        return false;
    }

    /**
     * 查找数据库并返回jwt
     * @param username 用户名（学号）
     * @param password 密码
     * @return 生成的 jwtToken
     */
    @Override
    public String login(String username, String password) {
        return null;
    }
}
