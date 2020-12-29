package com.my.workmanagement.controller;

import javax.websocket.server.PathParam;

import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.response.normalwork.TopicInfoResponse;
import com.my.workmanagement.service.interfaces.FileStorageService;
import com.my.workmanagement.service.interfaces.NormalWorkService;

import java.util.List;

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
    Logger logger = LoggerFactory.getLogger(TopicController.class);

    private final NormalWorkService normalWorkService;
    private final FileStorageService fileStorageService;

    @Autowired
    public TopicController(NormalWorkService normalWorkService, FileStorageService fileStorageService) {
        this.normalWorkService = normalWorkService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(value = "/{stuId}/{topicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> uploadNormalWork(
            MultipartFile file,
            @PathVariable Integer stuId,
            @PathVariable Integer topicId
    ) {
        logger.info("/normal uploadNormalWork");
        normalWorkService.store(stuId, topicId, file);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/{stuId}/{topicId}")
    public ResponseEntity<Resource> getNormalWork(
            @PathVariable Integer stuId,
            @PathVariable Integer topicId
    ) {
        Resource file = normalWorkService.loadResource(stuId, topicId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
                file.getFilename()).body(file);
    }

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
    @GetMapping(value = "/{topicId}")
    public ResponseEntity<PackedResponse<TopicInfoResponse>> getTopicInfo(
            @PathVariable("topicId") Integer topicId
    ) {
        TopicInfoResponse response = normalWorkService.getTopicInfo(topicId);
        return PackedResponse.success(response, "");
    }
}
