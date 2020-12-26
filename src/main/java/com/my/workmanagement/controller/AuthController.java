package com.my.workmanagement.controller;

import com.my.workmanagement.exception.UndefinedUserRoleException;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.LoginRequest;
import com.my.workmanagement.payload.response.JwtResponse;
import com.my.workmanagement.service.interfaces.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<PackedResponse<JwtResponse>> handleLogin(
            @RequestBody @Valid LoginRequest request
    ) throws UndefinedUserRoleException {
        logger.info("Trying login");
        logger.info("Username: {}, Password: {}, Role: {}",
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );

        String generatedToken = authService.login(request.getUsername(), request.getPassword(), request.getRole());

        JwtResponse response = JwtResponse.JwtResponseBuilder.aJwtResponse()
                .withToken(generatedToken)
                .build();
        return ResponseEntity.ok(PackedResponse.success(response, ""));
    }

    @RequestMapping("/hello")
    public ResponseEntity<PackedResponse<String>> hello() {
        return ResponseEntity.ok(PackedResponse.success("hello", "hello"));
    }
}
