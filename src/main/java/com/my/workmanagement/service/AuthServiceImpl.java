package com.my.workmanagement.service;

import com.my.workmanagement.model.ERole;
import com.my.workmanagement.model.WMUserDetails;
import com.my.workmanagement.service.interfaces.AuthService;
import com.my.workmanagement.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserDetailsService teacherService;
    private final UserDetailsService studentService;
    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(
            @Qualifier("studentDetailsService") UserDetailsService studentService,
            @Qualifier("teacherDetailsService") UserDetailsService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public String login(String username, String password, ERole role) {
        String token = "";
        UserDetails userDetails = null;

        if (role == ERole.ROLE_TEACHER) {
            login(new UsernamePasswordAuthenticationToken(username, password));
            userDetails = teacherService.loadUserByUsername(username);
            token = JwtUtils.generateToken((WMUserDetails) userDetails);
            logger.info("teacher[{}] 登录成功", username);
        } else if (role == ERole.ROLE_STUDENT) {
            login(new UsernamePasswordAuthenticationToken(username, password));
            userDetails = studentService.loadUserByUsername(username);
            token = JwtUtils.generateToken((WMUserDetails) userDetails);
            logger.info("student[{}] 登录成功", username);
        } else {
            logger.info("No User");
        }
        return token;
    }

    private void login(UsernamePasswordAuthenticationToken token) {
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
