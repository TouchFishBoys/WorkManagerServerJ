package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.UnsupportedFileTypeException;
import com.my.workmanagement.model.WMUserDetails;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.payload.response.student.CourseListResponse;
import com.my.workmanagement.payload.response.student.StudentInfoResponse;
import com.my.workmanagement.service.interfaces.CourseService;
import com.my.workmanagement.service.interfaces.StudentService;
import com.my.workmanagement.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService,
                             CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * 获取学生信息
     *
     * @param stuId 学生 Id
     * @return 学生信息
     */
    @GetMapping(value = "/{stuId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentInfoResponse> getStudentInfo(
            @PathVariable Integer stuId
    ) throws IdNotFoundException {
        StudentInfoBO studentInfoBO = studentService.getStudentInfo(stuId);

        StudentInfoResponse response = StudentInfoResponse.StudentInfoResponseBuilder.aStudentInfoResponse()
                .withStudentClass(studentInfoBO.getStudentClass())
                .withStudentName(studentInfoBO.getStudentName())
                .withStudentNum(studentInfoBO.getStudentNum())
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * 获取学生已选课程列表
     *
     * @return 学生参加的课程列表
     */
    @GetMapping("/course")
    public ResponseEntity<CourseListResponse> getCourseList(
    ) throws IdNotFoundException {
        WMUserDetails userDetails = AuthUtil.getUserDetail();
        Integer studentId = userDetails.getUserId();
        List<CourseInfoBO> courseInfoBOS = studentService.getSelectedCourseInfo(studentId);
        CourseListResponse response = new CourseListResponse();
        response.setCourses(courseInfoBOS);
        return ResponseEntity.ok(response);
    }

    /**
     * 导入学生
     *
     * @param excelFile 文件
     * @return 无
     */
    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole(T(com.my.workmanagement.model.ERole).ROLE_TEACHER)")
    public ResponseEntity<?> importStudents(
            @RequestParam(value = "excel", required = true) MultipartFile excelFile,
            @RequestParam(value = "course_name") String courseName,
            @RequestParam(value = "course_description") String courseDescription
    ) throws UnsupportedFileTypeException, IOException, IdNotFoundException {
        WMUserDetails userDetails = AuthUtil.getUserDetail();
        Integer teacherId = userDetails.getUserId();
        Integer courseId = courseService.createCourse(teacherId, courseName, courseDescription);
        boolean result = studentService.importStudents(courseId, excelFile);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/export/{courseId}", produces = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    public ResponseEntity<Resource> exportStudents(@PathVariable String courseId) {
        Resource excelFile = studentService.getStudentExcel(courseId);
        return ResponseEntity.ok(excelFile);
    }
}
