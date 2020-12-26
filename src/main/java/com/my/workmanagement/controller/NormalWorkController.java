package com.my.workmanagement.controller;

import javax.websocket.server.PathParam;

import com.my.workmanagement.exception.StorageFileNotFoundException;
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
@RequestMapping("/normal")
public class NormalWorkController {
    Logger logger = LoggerFactory.getLogger(NormalWorkController.class);

    private final NormalWorkService normalWorkService;

    @Autowired
    public NormalWorkController(NormalWorkService normalWorkService) {
        this.normalWorkService = normalWorkService;
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

    @GetMapping(value = "/{stuId}/{topicId}", produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Resource> getNormalWork(@PathParam("stuId") Integer stuId, @PathParam("topicId") Integer topicId) {
        Resource file = normalWorkService.loadResource(stuId, topicId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
                file.getFilename()).body(file);
    }

    @GetMapping(value = "/{stuId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<String>> getNormalWorkList(@PathParam("stuId") Integer stuId) {
        List<String> workList = normalWorkService.getStuSubmittedList(stuId);
        return ResponseEntity.ok(workList);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFound() {
        return ResponseEntity.notFound().build();
    }
}
