package com.my.workmanagement.controller;

import javax.websocket.server.PathParam;

import com.aliyun.oss.common.utils.StringUtils;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.exception.StorageIOException;
import com.my.workmanagement.model.UploadInfo;
import com.my.workmanagement.model.bo.NormalWorkBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import com.my.workmanagement.service.interfaces.FileStorageService;
import com.my.workmanagement.service.interfaces.NormalWorkService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.my.workmanagement.service.interfaces.StudentService;
import com.my.workmanagement.service.interfaces.UploadService;
import com.my.workmanagement.util.AuthUtil;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final NormalWorkService normalWorkService;
    private final FileStorageService fileStorageService;
    private final UploadService uploadService;
    private final StudentService studentService;
    Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    public TopicController(NormalWorkService normalWorkService,
                           FileStorageService fileStorageService,
                           UploadService uploadService,
                           StudentService studentService

    ) {
        this.normalWorkService = normalWorkService;
        this.fileStorageService = fileStorageService;
        this.uploadService = uploadService;
        this.studentService = studentService;
    }

    /**
     * 提交作业
     *
     * @param file    提交的文件
     * @param topicId 题目Id
     * @return /
     */
    @ApiOperation("提交作业")
    @PostMapping(value = "/{topicId}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PackedResponse<Void>> uploadNFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable Integer topicId
    ) throws IdNotFoundException, StorageIOException {
        Integer stuId = AuthUtil.getUserDetail().getUserId();
        if (normalWorkService.submit(topicId, stuId, file)) {
            return PackedResponse.success(null, "成功");
        } else {
            return PackedResponse.success(null, "文件已被覆盖");
        }
    }

    /**
     * 下载平时作业文件
     *
     * @param stuId   学生Id
     * @param topicId 题目Id
     * @return 一份平时作业
     * @throws StorageFileNotFoundException 文件未找到（可能还未上传）
     */
    @ApiOperation("下载作业")
    @GetMapping(value = "/{stuId}/{topicId}", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<Resource> getNormalWork(
            @PathVariable Integer stuId,
            @PathVariable Integer topicId,
            @PathParam("filename") String fileName
    ) throws StorageFileNotFoundException, IdNotFoundException {
        logger.debug("下载{}的平时作业{}", stuId, topicId);
        Resource file = normalWorkService.getNormalWorkFile(stuId, topicId);
        if (StringUtils.isNullOrEmpty(fileName)) {
            StudentInfoBO studentInfo = studentService.getStudentInfo(stuId);
            fileName = studentInfo.getStudentNum() + "-" + studentInfo.getStudentName() + "-" + studentInfo.getStudentClass() + ".zip";
            logger.debug(fileName);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" +
                        URLEncoder.encode(fileName, StandardCharsets.UTF_8))
                .body(file);
    }

    /**
     * 设置成绩
     *
     * @param score   成绩
     * @param stuId   学生Id
     * @param topicId 题目Id
     * @return null
     * @throws IdNotFoundException 记录不存在
     */
    @ApiOperation("作业评分")
    @PostMapping("/{stuId}/{topicId}/score")
    public ResponseEntity<PackedResponse<Void>> setNormalWorkScore(
            @RequestBody @Range(max = 100, min = 0, message = "Score must >= 0 and <=100") Integer score,
            @PathVariable Integer stuId,
            @PathVariable Integer topicId
    ) throws IdNotFoundException {
        if (normalWorkService.setScore(topicId, stuId, score)) {
            return PackedResponse.success(null, "成功");
        } else {
            return PackedResponse.conflict(null, "成绩已存在");
        }
    }

    /**
     * 获取学生上传的平时作业列表
     *
     * @param stuId 学生Id
     * @return 学生提交的平时作业列表
     */
    @ApiOperation("获取学生提交的作业")
    @GetMapping(value = "/{stuId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<String>> getNormalWorkList(
            @PathVariable Integer stuId
    ) {
        List<String> workList = normalWorkService.getStuSubmittedList(stuId);
        return ResponseEntity.ok(workList);
    }

    /**
     * 获取题目信息
     *
     * @param topicId 题目Id
     * @return 题目信息
     */
    @ApiOperation("获取题目信息")
    @GetMapping(value = "/{topicId}")
    public ResponseEntity<PackedResponse<TopicInfoResponse>> getTopicInfo(
            @PathVariable("topicId") Integer topicId
    ) throws IdNotFoundException {
        TopicInfoResponse response = normalWorkService.getTopicInfo(topicId);
        return PackedResponse.success(response, "");
    }

    /**
     * 获取作业列表
     *
     * @param topicId 题目Id
     * @return 作业列表
     */
    @ApiOperation("获取题目下作业列表")
    @GetMapping(value = "/{topicId}/normal-work")
    public ResponseEntity<PackedResponse<List<NormalWorkBO>>> getNormalWorkList(
            @PathVariable("topicId") Integer topicId,
            @PathParam("finished") boolean finished
    ) throws IdNotFoundException {
        List<NormalWorkBO> list;

        if (!finished) {
            list = normalWorkService.getNormalWork_Topic(topicId);
        } else {
            list = normalWorkService.getFinishedNormalWork_Topic(topicId);
        }
        return PackedResponse.success(list, "ok");
    }
}
