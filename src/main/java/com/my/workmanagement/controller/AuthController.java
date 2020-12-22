package com.my.workmanagement.controller;

import com.my.workmanagement.payload.response.JwtResponse;
import com.my.workmanagement.service.StudentService;
import com.my.workmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public AuthController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> handleLogin() {
        JwtResponse response = new JwtResponse();
        return ResponseEntity.ok(response);
    }
}
