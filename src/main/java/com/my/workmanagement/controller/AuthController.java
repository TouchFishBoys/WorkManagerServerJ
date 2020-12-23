package com.my.workmanagement.controller;

import com.my.workmanagement.exception.UndefinedUserRoleException;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.LoginRequest;
import com.my.workmanagement.payload.response.JwtResponse;
import com.my.workmanagement.service.AuthService;
import com.my.workmanagement.service.StudentService;
import com.my.workmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/auth")
public class AuthController {
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public AuthController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @PostMapping("/login")
    public ResponseEntity<PackedResponse<JwtResponse>> handleLogin(
            @RequestBody @Valid LoginRequest request
    ) throws UndefinedUserRoleException {

        AuthService authService;
        switch (request.getRole()) {
            case TEACHER:
                authService = (AuthService) teacherService;
                break;
            case STUDENT:
                authService = (AuthService) studentService;
                break;
            default:
                throw new UndefinedUserRoleException(request.getRole().name());
        }
        String generatedToken = authService.login(request.getUserName(), request.getPassword());
        JwtResponse response = JwtResponse.JwtResponseBuilder.aJwtResponse()
                .withToken(generatedToken)
                .build();
        return ResponseEntity.ok(new PackedResponse<>(response));
    }
}
