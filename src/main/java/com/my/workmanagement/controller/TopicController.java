package com.my.workmanagement.controller;

import javax.websocket.server.PathParam;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.model.UploadInfo;
import com.my.workmanagement.model.WMUserDetails;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import com.my.workmanagement.service.interfaces.FileStorageService;
import com.my.workmanagement.service.interfaces.NormalWorkService;

import java.util.List;

import com.my.workmanagement.service.interfaces.UploadService;
import com.my.workmanagement.util.AuthUtil;
import com.my.workmanagement.util.FilePathUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/topic")
public class TopicController {
    Logger logger = LoggerFactory.getLogger(TopicController.class);

    private final NormalWorkService normalWorkService;
    private final FileStorageService fileStorageService;
    private final UploadService uploadService;

    @Autowired
    public TopicController(NormalWorkService normalWorkService, FileStorageService fileStorageService, UploadService uploadService) {
        this.normalWorkService = normalWorkService;
        this.fileStorageService = fileStorageService;
        this.uploadService = uploadService;
    }

    /**
     * 提交作业
     *
     * @param file    提交的文件
     * @param topicId 题目Id
     * @return /
     */
    @ApiOperation("上传作业")
    @PostMapping(value = "/{topicId}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadInfo uploadNFile(@RequestParam("file") MultipartFile file, @PathVariable Integer topicId) {
        UploadInfo uploadInfo = null;
        Integer stuId = AuthUtil.getUserDetail().getUserId();
        try {
            uploadInfo = uploadService.uploadNFile(file, "", topicId, stuId);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return uploadInfo;
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
    ) throws StorageFileNotFoundException {
        Resource file = normalWorkService.loadResource(stuId, topicId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFilename())
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
}
