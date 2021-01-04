package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.model.bo.FinalWorkBO;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.SingleValueRequest;
import com.my.workmanagement.payload.request.finalwork.SetDocumentScoreRequest;
import com.my.workmanagement.service.interfaces.FinalWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/final")
@Api(description = "大作业")
public class FinalWorkController {
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);
    private final FinalWorkService finalWorkService;

    @Autowired
    public FinalWorkController(FinalWorkService finalWorkService) {
        this.finalWorkService = finalWorkService;
    }

    /**
     * 获取大作业信息
     *
     * @param teamId 队伍Id
     * @return 大作业信息
     * @throws IdNotFoundException 队伍不存在
     */
    @GetMapping(value = "/{teamId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "获取大作业信息"
    )
    public ResponseEntity<PackedResponse<FinalWorkBO>> getFinalWorkInfo(@PathVariable Integer teamId) throws IdNotFoundException {
        FinalWorkBO response = finalWorkService.getFinalWorkInfo(teamId);
        // TODO: 2020/12/29 增加总人数和完成人数
        return PackedResponse.success(response, "");
    }

    /**
     * 设置大作业分数
     *
     * @param finalId 大作业Id
     * @param request 请求
     * @return httpok or conflict(成绩已经设置)
     * @throws IdNotFoundException 大作业Id不存在
     */
    @PostMapping(value = "/{finalId}/score",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole(T(com.my.workmanagement.model.ERole).ROLE_TEACHER)")
    public ResponseEntity<PackedResponse<Void>> setFinalWorkScore(
            @PathVariable Integer finalId,
            @ApiParam("成绩") @RequestBody SingleValueRequest<Integer> request
    ) throws IdNotFoundException {
        Integer score = request.getValue();
        if (score < 0 || score > 100) {
            return PackedResponse.badRequest(null, "Score must in [0,100]");
        }
        if (finalWorkService.setFinalWorkScore(finalId, request.getValue())) {
            return PackedResponse.success(null, "Success");
        } else {
            return PackedResponse.failure(null, "Score has been set", HttpStatus.CONFLICT);
        }
    }

    /**
     * 下载大作业文档
     *
     * @param finalId 大作业Id
     * @return 大作业文档
     * @throws StorageFileNotFoundException 文档不存在
     */
    @GetMapping(value = "/{finalId}/document", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<Resource> getFinalWorkDocument(
            @PathVariable Integer finalId,
            @PathParam("fileName") String fileName
    ) throws StorageFileNotFoundException {
        Resource resource;
        try {
            resource = finalWorkService.loadDocumentByFworkId(finalId);
        } catch (FileNotFoundException e) {
            throw new StorageFileNotFoundException();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s.docx\"", fileName))
                .header(HttpHeaders.CACHE_CONTROL, "no-cache,no-store,must-revalidate")
                .body(resource);
    }

    @GetMapping(value = "/{finalId}/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getFinalWorkFile(
            @PathVariable Integer finalId,
            @PathParam("filename") String fileName
    ) throws StorageFileNotFoundException {
        Resource resource;
        try {
            resource = finalWorkService.loadFworkFileByFworkId(finalId);
        } catch (FileNotFoundException e) {
            throw new StorageFileNotFoundException();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s.docx\"", fileName))
                .header(HttpHeaders.CACHE_CONTROL, "no-cache,no-store,must-revalidate")
                .body(resource);
    }

    /**
     * 设置大作业文档分数
     *
     * @param finalId 大作业Id
     * @param request 分数
     * @return null
     * @throws IdNotFoundException 大作业不存在
     */
    @PostMapping(value = "/{finalId}/document/score", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PackedResponse<Void>> setFworkDocumentScore(
            @PathVariable Integer finalId,
            @RequestBody SetDocumentScoreRequest request
    ) throws IdNotFoundException {
        if (finalWorkService.setDocumentScore(finalId, request.getScore())) {
            return PackedResponse.success(null, String.format("Set document score success"));
        } else {
            return PackedResponse.failure(null, "Score already set", HttpStatus.CONFLICT);
        }
    }
}
