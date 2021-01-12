package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.exception.WordTemplateNotFoundException;
import com.my.workmanagement.model.bo.QaTableBO;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.SubmitQaTableRequest;
import com.my.workmanagement.service.interfaces.FileStorageService;
import com.my.workmanagement.service.interfaces.FinalWorkService;
import com.my.workmanagement.service.interfaces.StudentService;
import com.my.workmanagement.service.interfaces.TeamService;
import com.my.workmanagement.util.FilePathUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/qa-table")
public class QaTableController {
    private final FileStorageService fileStorageService;
    private final TeamService teamService;
    private final FinalWorkService finalWorkService;
    private final StudentService studentService;

    @Autowired
    public QaTableController(
            FileStorageService fileStorageService,
            TeamService teamService,
            FinalWorkService finalWorkService,
            StudentService studentService
    ) {
        this.fileStorageService = fileStorageService;
        this.teamService = teamService;
        this.finalWorkService = finalWorkService;
        this.studentService=studentService;
    }

    /**
     * 获取答辩记录表（JSON）
     *
     * @param courseId  课程Id
     * @param studentId 学生Id
     * @return /
     */
    @ApiOperation("获取答辩记录表")
    @GetMapping("/{courseId}/{studentId}")
    public ResponseEntity<PackedResponse<String>> getQaTableJson(
            @PathVariable String courseId,
            @PathVariable String studentId
    ) {
        // TODO: 2020/12/29 从Word文件解析json
        return PackedResponse.success("todo", "todo");
    }

    /**
     * 下载答辩记录表
     *
     * @param courseId  课程Id
     * @param studentId 学生Id
     * @return 答辩记录
     * @throws IdNotFoundException          没找到Id
     * @throws StorageFileNotFoundException 文件没找到（还没上传）
     */
    @ApiOperation("下载答辩记录表")
    @GetMapping(value = "/{courseId}/{studentId}/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getQaTableFile(
            @PathVariable Integer courseId,
            @PathVariable Integer studentId,
            @RequestParam String fileName
    ) throws IdNotFoundException, StorageFileNotFoundException {
        FilePathUtil.FilePathBuilder pathBuilder = FilePathUtil.FilePathBuilder.builder();
        Integer teamId = teamService.getTeamId(studentId, courseId);

        String studentNum=studentService.getStudentInfo(studentId).getStudentNum();
        pathBuilder.enter(courseId.toString())
                .enter("final")
                .enter(teamId.toString())
                .enter("qa-table")
                .enter(studentNum+".docx");
        String paths=pathBuilder.build();
        Resource resource = fileStorageService.loadAsResource(paths); // 读取文件
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s.docx\"", fileName))
                .header(HttpHeaders.CACHE_CONTROL, "no-cache,no-store,must-revalidate")
                .body(resource);
    }

    /**
     * 生成答辩记录表
     *
     * @param courseId  课程Id
     * @param studentId 学生Id
     * @param request   request
     * @return /
     * @throws WordTemplateNotFoundException 模板没找到
     */
    @ApiOperation("生成答辩记录表")
    @PostMapping(value = "/{courseId}/{studentId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PostAuthorize("hasRole(T(com.my.workmanagement.model.ERole).ROLE_TEACHER)")
    public ResponseEntity<PackedResponse<Void>> submitQaTable(
            @PathVariable @Min(value = 1, message = "course id must > 0") Integer courseId,
            @PathVariable @Min(value = 1, message = "student id must > 0") Integer studentId,
            @RequestBody SubmitQaTableRequest request
    ) throws WordTemplateNotFoundException, IOException, IdNotFoundException {
        List<QaTableBO.QaTableItemBO> qaItems = request.getQaList();
        finalWorkService.setQAScore(courseId,studentId,request.getScore());
        if (finalWorkService.generateQaTable(qaItems, courseId, studentId, request.getQaLocation(), request.getScore())) {
            return PackedResponse.success(null, "success");
        } else {
            return PackedResponse.failure(null, "failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
