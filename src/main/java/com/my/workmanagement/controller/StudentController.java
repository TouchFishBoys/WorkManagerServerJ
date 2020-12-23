package com.my.workmanagement.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.my.workmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/import", consumes = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/vnd.ms-excel"})
    @PreAuthorize("hasRole(T(model.ERole).ROLE_TEACHER)")
    public ResponseEntity<?> importStudents(@RequestParam(value = "excel", required = true) MultipartFile excelFile) {
        boolean result = studentService.importStudents(excelFile);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/export/{courseId}", produces = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    public ResponseEntity<Resource> exportStudents(@PathParam(value = "courseId") String courseId) {
        Resource excelFile = studentService.getStudentExcel(courseId);
        return ResponseEntity.ok(excelFile);
    }
}
