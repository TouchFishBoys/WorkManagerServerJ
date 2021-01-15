package com.my.workmanagement.controller;

import com.aliyun.oss.common.utils.AuthUtils;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.model.UploadInfo;
import com.my.workmanagement.model.bo.CourseInfoBO;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.SingleValueRequest;
import com.my.workmanagement.payload.request.finalwork.SetDocumentScoreRequest;
import com.my.workmanagement.service.interfaces.FinalWorkService;
import com.my.workmanagement.service.interfaces.UploadService;
import com.my.workmanagement.util.AuthUtil;
import com.my.workmanagement.util.FilePathUtil;
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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/final")
@Api(description = "大作业")
public class FinalWorkController {
    HttpServletRequest request;
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);
    private final FinalWorkService finalWorkService;
    private final UploadService uploadService;

    @Autowired
    public FinalWorkController(FinalWorkService finalWorkService, UploadService uploadService) {
        this.finalWorkService = finalWorkService;
        this.uploadService = uploadService;
    }

    /**
     * 设置大作业分数
     *
     * @param finalId 大作业Id
     * @param request 请求
     * @return httpok or conflict(成绩已经设置)
     * @throws IdNotFoundException 大作业Id不存在
     */
    @ApiOperation("大作业评分")
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
    @ApiOperation("下载文档")
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
        if (fileName == null) {
            fileName = "document";
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s.docx\"", fileName))
                .header(HttpHeaders.CACHE_CONTROL, "no-cache,no-store,must-revalidate")
                .body(resource);
    }

    /**
     * 下载大作业文件
     *
     * @param finalId 大作业Id
     * @return 大作业文件
     * @throws StorageFileNotFoundException 文档不存在
     */
    @ApiOperation("下载文件")
    @GetMapping(value = "/{finalId}/file",
            produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<Resource> getFinalWorkFile(
            @PathVariable Integer finalId,
            @PathParam("fileName") String fileName
    ) throws StorageFileNotFoundException {
        Resource resource;
        resource = finalWorkService.loadFworkFileByFworkId(finalId);
        if (fileName == null) {
            fileName = "FinalWork";
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s.war\"", fileName))
                .header(HttpHeaders.CACHE_CONTROL, "no-cache,no-store,must-revalidate")
                .body(resource);
    }

    /*
    @ApiOperation("下载上传的文件")
    @GetMapping(value = "/{finalId}/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getFinalWorkFile(
            @PathVariable Integer finalId,
            @PathParam("filename") String fileName
    ) throws StorageFileNotFoundException {
        Resource resource;
        resource = finalWorkService.loadFworkFileByFworkId(finalId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s.war\"", fileName))
                .header(HttpHeaders.CACHE_CONTROL, "no-cache,no-store,must-revalidate")
                .body(resource);
    }*/

    /**
     * 设置大作业文档分数
     *
     * @param finalId 大作业Id
     * @param request 分数
     * @return null
     * @throws IdNotFoundException 大作业不存在
     */
    @ApiOperation("文档评分")
    @PostMapping(value = "/{finalId}/document/score", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PackedResponse<Void>> setFworkDocumentScore(
            @PathVariable Integer finalId,
            @ApiParam("成绩") @RequestBody SingleValueRequest<Integer> request
    ) throws IdNotFoundException {
        if (finalWorkService.setDocumentScore(finalId, request.getValue())) {
            return PackedResponse.success(null, String.format("Set document score success"));
        } else {
            return PackedResponse.failure(null, "Score already set", HttpStatus.CONFLICT);
        }
    }

    @ApiOperation("上传文档")
    @PostMapping(value = "/document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PackedResponse<Void>> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("courseId") Integer courseId
    ) throws IdNotFoundException {
        Integer studentId = AuthUtil.getUserDetail().getUserId();

        try {
            finalWorkService.uploadDocument(courseId, studentId, file);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            return PackedResponse.failure(null, "IOException", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return PackedResponse.success(null, "success");
    }

    @ApiOperation("上传大作业")
    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostAuthorize("hasRole(T(com.my.workmanagement.model.ERole).ROLE_STUDENT)")
    public ResponseEntity<PackedResponse<Void>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("courseId") Integer courseId
    ) throws IdNotFoundException {
        Integer studentId = AuthUtil.getUserDetail().getUserId();

        try {
            finalWorkService.uploadFinalWork(courseId, studentId, file);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            return PackedResponse.failure(null, "IOException", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return PackedResponse.success(null, "success");
    }

    @GetMapping("/{finalId}/authors")
    public ResponseEntity<PackedResponse<List<StudentInfoBO>>> getFinalAuthor(
            @PathVariable Integer finalId
    ) throws IdNotFoundException {
        List<StudentInfoBO> studentInfos = finalWorkService.getAuthors(finalId);
        return PackedResponse.success(studentInfos, "success");
    }

    @GetMapping("/{finalId}/course")
    public ResponseEntity<PackedResponse<CourseInfoBO>> getCourseInfo() {
        CourseInfoBO courseInfo = new CourseInfoBO();
        return PackedResponse.success(courseInfo, "success");
    }
}
