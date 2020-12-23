package com.my.workmanagement.service;

import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService, AuthService{
    /**
     * 登录并返回jwt
     * @param username 用户名（工号）
     * @param password 密码
     * @return jwtToken
     */
    @Override
    public String login(String username, String password) {
        return null;
    }
}
