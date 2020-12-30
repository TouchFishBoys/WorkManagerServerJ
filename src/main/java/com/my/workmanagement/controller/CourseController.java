package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.UndefinedUserRoleException;
import com.my.workmanagement.model.ERole;
import com.my.workmanagement.model.WMUserDetails;
import com.my.workmanagement.model.bo.TopicInfoBO;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.topic.ReleaseTopicRequest;
import com.my.workmanagement.payload.response.CourseInfoResponse;
import com.my.workmanagement.payload.response.TopicInfoListResponse;
import com.my.workmanagement.payload.response.student.StudentInfoResponse;
import com.my.workmanagement.service.interfaces.CourseService;
import com.my.workmanagement.service.interfaces.NormalWorkService;
import com.my.workmanagement.service.interfaces.StudentService;
import com.my.workmanagement.util.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final CourseService courseService;
    private final NormalWorkService normalWorkService;

    @Autowired
    public CourseController(
            StudentService studentService,
            CourseService courseService,
            NormalWorkService normalWorkService
    ) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.normalWorkService = normalWorkService;
    }

    /**
     * 获取课程信息
     *
     * @param courseId 课程 Id
     * @return 课程信息（ID,名称，教师，描述，学生数量，年份）
     */
    @GetMapping("/{courseId}")
    public ResponseEntity<PackedResponse<CourseInfoResponse>> getCourseInfo(
            @PathVariable Integer courseId
    ) throws IdNotFoundException {
        logger.info("Getting course info: {}", courseId);
        CourseInfoResponse response = courseService.getCourseInfo(courseId);
        return PackedResponse.success(response, "");
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
        return PackedResponse.success(response, "");
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
            @PathVariable Integer courseId
    ) {
        boolean result = studentService.importStudents(courseId, excelFile);
        return ResponseEntity.ok(result);
    }

    /**
     * 发布题目
     *
     * @param request  发布的题目信息
     * @param courseId 课程 ID
     * @return 发布的题目的 topicId
     */
    @PostMapping("/{courseId}/topic")
    public ResponseEntity<PackedResponse<Integer>> releaseTopic(
            @RequestBody ReleaseTopicRequest request,
            @PathVariable Integer courseId
    ) {
        Integer result = normalWorkService.createTopic(
                request.getTopicName(),
                request.getTopicDescription(),
                courseId,
                request.getStartTime(),
                request.getFinishTime()
        );
        if (result == null) {
            return PackedResponse.failure(-1, "Create failed", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return PackedResponse.success(
                    result,
                    String.format("Create topic %s success", request.getTopicName())
            );
        }
    }

    /**
     * 获取 TopicInfoList
     *
     * @param courseId 课程 Id
     * @return 题目信息列表
     * @throws UndefinedUserRoleException 用户角色不存在
     * @throws IdNotFoundException        没有找到对应的记录
     */
    @GetMapping("/{courseId}/topic")
    public ResponseEntity<PackedResponse<TopicInfoListResponse>> getTopicInfoList(
            @PathVariable Integer courseId
    ) throws UndefinedUserRoleException, IdNotFoundException {
        TopicInfoListResponse response = new TopicInfoListResponse();
        WMUserDetails userDetails = AuthUtil.getUserDetail();
        if (userDetails.getRole() == ERole.ROLE_STUDENT) {
            response.setTopics(normalWorkService.getTopicInfosAsStudent(courseId, userDetails.getUserId()));
        } else if (userDetails.getRole() == ERole.ROLE_TEACHER) {
            response.setTopics(normalWorkService.getTopicInfosAsTeacher(courseId, userDetails.getUserId()));
        } else {
            throw new UndefinedUserRoleException(userDetails.getRole().name());
        }
        return PackedResponse.success(response, "");
    }
    /**
     * 获取课程题目列表
     *
     * @param courseId 课程 ID
     * @return 题目列表
     */
    @GetMapping("/{courseId}/topic")
    public ResponseEntity<PackedResponse<CourseInfoResponse>> getTopics(
            @RequestBody ReleaseTopicRequest request,
            @PathVariable Integer courseId
    ) throws IdNotFoundException {
        List<TopicInfoBO> topicInfoBOS=courseService.getTopicInfoByCourseId(courseId);
        return null;
    }
}

