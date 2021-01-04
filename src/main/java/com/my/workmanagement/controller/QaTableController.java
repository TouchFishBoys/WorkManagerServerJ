package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.exception.WordTemplateNotFoundException;
import com.my.workmanagement.model.bo.QaTableBO;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.SubmitQaTableRequest;
import com.my.workmanagement.service.interfaces.FileStorageService;
import com.my.workmanagement.service.interfaces.FinalWorkService;
import com.my.workmanagement.service.interfaces.TeamService;
import com.my.workmanagement.util.FilePathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public QaTableController(
            FileStorageService fileStorageService,
            TeamService teamService,
            FinalWorkService finalWorkService
    ) {
        this.fileStorageService = fileStorageService;
        this.teamService = teamService;
        this.finalWorkService = finalWorkService;
    }

    /**
     * 获取答辩记录表（JSON）
     *
     * @param courseId  课程Id
     * @param studentId 学生Id
     * @return /
     */
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
    @GetMapping(value = "/{courseId}/{studentId}/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getQaTableFile(
            @PathVariable Integer courseId,
            @PathVariable Integer studentId,
            @RequestParam String fileName
    ) throws IdNotFoundException, StorageFileNotFoundException {
        FilePathUtil.FilePathBuilder pathBuilder = FilePathUtil.FilePathBuilder.builder();
        Integer teamId = teamService.getTeamId(studentId, courseId);

        pathBuilder.enter(courseId.toString())
                .enter("final")
                .enter(teamId.toString())
                .enter("qa-table.docx");

        Resource resource = fileStorageService.loadAsResource(pathBuilder.build()); // 读取文件
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
    @PostMapping(value = "/{courseId}/{studentId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PackedResponse<Void>> submitQaTable(
            @PathVariable @Min(value = 1, message = "course id must > 0") Integer courseId,
            @PathVariable @Min(value = 1, message = "student id must > 0") Integer studentId,
            @RequestBody SubmitQaTableRequest request
    ) throws WordTemplateNotFoundException, IOException, IdNotFoundException {
        List<QaTableBO.QaTableItemBO> qaItems = request.getQaList();
        // TODO: 2020/12/29  生成答辩记录表
        if (finalWorkService.generateQaTable(qaItems, courseId, studentId, request.getQaLocation(), request.getScore())) {
            return PackedResponse.success(null, "success");
        } else {
            return PackedResponse.failure(null, "failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
