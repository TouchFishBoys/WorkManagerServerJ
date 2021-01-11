package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.UndefinedUserRoleException;
import com.my.workmanagement.model.ERole;
import com.my.workmanagement.model.WMUserDetails;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.FinalWorkBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.topic.ReleaseTopicRequest;
import com.my.workmanagement.payload.response.CourseInfoResponse;
import com.my.workmanagement.payload.response.TopicInfoListResponse;
import com.my.workmanagement.payload.response.student.CourseListResponse;
import com.my.workmanagement.payload.response.student.StudentInfoResponse;
import com.my.workmanagement.service.interfaces.CourseService;
import com.my.workmanagement.service.interfaces.NormalWorkService;
import com.my.workmanagement.service.interfaces.StudentService;
import com.my.workmanagement.service.interfaces.TeacherService;
import com.my.workmanagement.util.AuthUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final StudentService studentService;
    private final CourseService courseService;
    private final NormalWorkService normalWorkService;
    private final TeacherService teacherService;

    @Autowired
    public CourseController(
            StudentService studentService,
            TeacherService teacherService,
            CourseService courseService,
            NormalWorkService normalWorkService
    ) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.normalWorkService = normalWorkService;
        this.teacherService = teacherService;
    }

    @ApiOperation("获取课程列表")
    @GetMapping
    public ResponseEntity<PackedResponse<CourseListResponse>> getJoinedCourse(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) throws UndefinedUserRoleException, IdNotFoundException {
        CourseListResponse response = new CourseListResponse();
        WMUserDetails userDetails = AuthUtil.getUserDetail();
        logger.info("Userid:{}", userDetails.getUserId());
        Pageable pageable = PageRequest.of(page - 1, size);

        switch (userDetails.getRole()) {
            case ROLE_TEACHER:
                response = teacherService.getHostedCourseInfoList(userDetails.getUserId(), pageable);
                break;
            case ROLE_STUDENT:
                response = studentService.getHostedCourseInfoList(userDetails.getUserId(),pageable);
                break;
            default:
                throw new UndefinedUserRoleException(AuthUtil.getUserDetail().getRole().name());
        }
        return PackedResponse.success(response, "ok");
    }

    /**
     * 获取课程信息
     *
     * @param courseId 课程 Id
     * @return 课程信息
     */
    @ApiOperation("获取课程信息")
    @GetMapping("/{courseId}")
    public ResponseEntity<PackedResponse<CourseInfoResponse>> getCourseInfo(
            @PathVariable Integer courseId
    ) throws IdNotFoundException, UndefinedUserRoleException {
        logger.info("Getting course info: {}", courseId);
        WMUserDetails userDetails = AuthUtil.getUserDetail();
        CourseInfoResponse response = new CourseInfoResponse();
        if (userDetails.getRole() == ERole.ROLE_TEACHER) {
            CourseInfoBO courseInfoBO = courseService.getCourseInfo_Teacher(courseId);
            response = CourseInfoResponse.CourseInfoResponseBuilder.aCourseInfoResponse()
                    .withCourseId(courseId)
                    .withCourseName(courseInfoBO.getCourseName())
                    .withCourseYear(courseInfoBO.getCourseYear())
                    .withCourseDescription(courseInfoBO.getCourseDescription())
                    .withTeacherName(courseInfoBO.getCourseTeacherName())
                    .withTotalCount(courseInfoBO.getTotalCount())
                    .withFinishCount(courseInfoBO.getFinishCount())
                    .withStudentCount(courseInfoBO.getStudentCount())
                    .build();
        } else {
            if (userDetails.getRole() == ERole.ROLE_STUDENT) {
                CourseInfoBO courseInfoBO = courseService.getCourseInfo_Student(courseId, userDetails.getUserId());
                response = CourseInfoResponse.CourseInfoResponseBuilder.aCourseInfoResponse()
                        .withCourseId(courseId)
                        .withCourseName(courseInfoBO.getCourseName())
                        .withCourseYear(courseInfoBO.getCourseYear())
                        .withCourseDescription(courseInfoBO.getCourseDescription())
                        .withTeacherName(courseInfoBO.getCourseTeacherName())
                        .withTotalCount(courseInfoBO.getTotalCount())
                        .withFinishCount(courseInfoBO.getFinishCount())
                        .withStudentCount(courseInfoBO.getStudentCount())
                        .build();
            } else {
                logger.info("No User");
                throw new UndefinedUserRoleException("");
            }
        }
        return PackedResponse.success(response, "");
    }

    /**
     * 获取学生信息列表
     *
     * @param courseId 课程编号
     * @return 学生信息列表
     */
    @ApiOperation("获取学生列表（详情）")
    @GetMapping("/{courseId}/student")
    public ResponseEntity<PackedResponse<List<StudentInfoResponse>>> getStudentInfoList(
            @PathVariable Integer courseId,
            @PathParam("page") Integer page,
            @PathParam("size") Integer size
    ) throws IdNotFoundException {
        logger.info("Get student info list");
        List<StudentInfoResponse> response = new LinkedList<>();
        List<StudentInfoBO> studentInfoBOS = courseService.getStudentInfo(courseId);
        for (StudentInfoBO student : studentInfoBOS) {
            response.add(StudentInfoResponse.StudentInfoResponseBuilder.aStudentInfoResponse()
                    .withStudentClass(student.getStudentClass())
                    .withStudentNum(student.getStudentNum())
                    .withStudentName(student.getStudentName())
                    .build());
        }

        return PackedResponse.success(response, "");
    }

    /**
     * 发布题目
     *
     * @param request  发布的题目信息
     * @param courseId 课程 ID
     * @return 发布的题目的 topicId
     */
    @ApiOperation("发布题目")
    @PostMapping("/{courseId}/topic")
    public ResponseEntity<PackedResponse<Integer>> releaseTopic(
            @RequestBody ReleaseTopicRequest request,
            @PathVariable Integer courseId
    ) throws IdNotFoundException {
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
     * @throws IdNotFoundException 没有找到对应的记录
     */
    @ApiOperation("获取题目列表")
    @GetMapping("/{courseId}/topic")
    public ResponseEntity<PackedResponse<TopicInfoListResponse>> getTopicInfoList(
            @PathVariable Integer courseId
    ) throws IdNotFoundException {
        TopicInfoListResponse response = new TopicInfoListResponse();
        response.setTopics(normalWorkService.getTopicInfos(courseId));

        return PackedResponse.success(response, "");
    }

    /**
     * 获取 TopicInfoList
     *
     * @param courseId 课程 Id
     * @param finished
     * @return 题目信息列表
     * @throws IdNotFoundException 没有找到对应的记录
     */
    @ApiOperation("获取题目列表")
    @GetMapping("/{courseId}/final-work")
    public ResponseEntity<PackedResponse<List<FinalWorkBO>>> getFinalWorkList(
            @PathVariable Integer courseId,
            @PathParam("finished") boolean finished
    ) throws IdNotFoundException {

        List<FinalWorkBO> list = new ArrayList<>();
        if (finished) {
            list = courseService.getFinishedFinalWorkList(courseId);
        } else {
            list = courseService.getFinalWorkList(courseId);
        }
        return PackedResponse.success(list, "ok");
    }
}

