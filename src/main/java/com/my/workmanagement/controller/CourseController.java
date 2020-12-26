package com.my.workmanagement.controller;

import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.response.CourseInfoResponse;
import com.my.workmanagement.payload.response.student.StudentInfoResponse;
import com.my.workmanagement.service.interfaces.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final StudentService studentService;

    @Autowired
    public CourseController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 获取课程信息
     *
     * @param courseId 课程 Id
     * @return 课程信息
     */
    @GetMapping("/{courseId}")
    public ResponseEntity<PackedResponse<CourseInfoResponse>> getCourseInfo(@PathVariable Integer courseId) {
        logger.info("Getting course info: {}", courseId);
        CourseInfoResponse response = new CourseInfoResponse();
        return ResponseEntity.ok(PackedResponse.success(response, ""));
    }

    /**
     * 获取学生信息列表
     *
     * @param courseId 课程编号
     * @return 学生信息列表
     */
    @GetMapping("/{courseId}/student")
    public ResponseEntity<PackedResponse<List<StudentInfoResponse>>> getStudentInfoList(
            @PathVariable Integer courseId
    ) {
        logger.info("Get student info list");
        List<StudentInfoResponse> response = new LinkedList<>();
        return ResponseEntity.ok(PackedResponse.success(response, ""));
    }

    /**
     * 导入学生
     *
     * @param excelFile excel文件
     * @param courseId  课程 Id
     * @return ？
     */
    @PostMapping(value = "/{courseId}/student",
            consumes = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel"})
    @PreAuthorize("hasRole(T(com.my.workmanagement.model.ERole).ROLE_TEACHER)")
    public ResponseEntity<?> importStudents(
            @RequestParam(value = "file", required = true) MultipartFile excelFile,
            @PathVariable String courseId) {
        boolean result = studentService.importStudents(excelFile);
        return ResponseEntity.ok(result);
    }
}
