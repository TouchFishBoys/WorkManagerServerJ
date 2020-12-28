package com.my.workmanagement.controller;

import com.my.workmanagement.payload.response.student.CourseListResponse;
import com.my.workmanagement.payload.response.student.StudentInfoResponse;
import com.my.workmanagement.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 获取学生信息
     *
     * @param stuId 学生 Id
     * @return 学生信息
     */
    @GetMapping(value = "/{stuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentInfoResponse> getStudentInfo(
            @PathVariable Integer stuId,
            @RequestParam List<String> selectedColumn
            ) {


        return null;
    }

    /**
     * 获取学生参加的课程列表
     *
     * @param stuId 学生 Id
     * @return 学生参加的课程列表
     */
    @GetMapping("/{stuId}/course")
    public ResponseEntity<CourseListResponse> getCourseList(
            @PathVariable Integer stuId
    ) {
        CourseListResponse response = new CourseListResponse();
        return ResponseEntity.ok(response);
    }

    /**
     * 导入学生
     *
     * @param excelFile 文件
     * @return 无
     */
    @PostMapping(value = "/{courseId}", consumes = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/vnd.ms-excel"})
    @PreAuthorize("hasRole(T(com.my.workmanagement.model.ERole).ROLE_TEACHER)")
    public ResponseEntity<?> importStudents(
            @RequestParam(value = "excel", required = true) MultipartFile excelFile,
            @PathVariable Integer courseId
    ) {
        boolean result = studentService.importStudents(courseId, excelFile);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/export/{courseId}", produces = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    public ResponseEntity<Resource> exportStudents(@PathVariable String courseId) {
        Resource excelFile = studentService.getStudentExcel(courseId);
        return ResponseEntity.ok(excelFile);
    }
}
