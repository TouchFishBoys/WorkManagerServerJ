package com.my.workmanagement.controller;

import com.my.workmanagement.exception.UndefinedUserRoleException;
import com.my.workmanagement.model.WMUserDetails;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.LoginRequest;
import com.my.workmanagement.payload.response.JwtResponse;
import com.my.workmanagement.payload.response.WhoAmIResponse;
import com.my.workmanagement.service.interfaces.AuthService;
import com.my.workmanagement.util.AuthUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Api("用户验证接口")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("登录")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
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
        return PackedResponse.success(response, "");
    }

    @ApiOperation("获取用户角色")
    @GetMapping("/who-am-i")
    public ResponseEntity<PackedResponse<WhoAmIResponse>> hello() {
        WMUserDetails user = AuthUtil.getUserDetail();

        WhoAmIResponse response = new WhoAmIResponse(user.getUsername(), user.getRole());
        return PackedResponse.success(response, "hello");
    }

    @GetMapping("/am-i-a-teacher")
    @PreAuthorize("hasRole(T(com.my.workmanagement.model.ERole).ROLE_TEACHER)")
    public ResponseEntity<PackedResponse<String>> helloTeacher() {
        return PackedResponse.success("hello", "hello");
    }

    @GetMapping("/am-i-a-student")
    @PreAuthorize("hasRole(T(com.my.workmanagement.model.ERole).ROLE_STUDENT)")
    public ResponseEntity<PackedResponse<String>> helloStudent() {
        return PackedResponse.success("hello", "hello");
    }
}
