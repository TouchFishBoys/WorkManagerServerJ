package com.my.workmanagement.controller;

import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.response.finalWork.FinalWorkInfoResponse;
import com.my.workmanagement.service.interfaces.FinalWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/final-work")
public class FinalWorkController {
    Logger logger = LoggerFactory.getLogger(NormalWorkController.class);
    private final FinalWorkService finalWorkService;

    @Autowired
    public FinalWorkController(FinalWorkService finalWorkService) {
        this.finalWorkService = finalWorkService;
    }

    @GetMapping(value = "/{teamId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PackedResponse<FinalWorkInfoResponse>> getFinalWorkInfo(@PathVariable Integer teamId) {
        FinalWorkInfoResponse response = finalWorkService.getFinalWorkInfo(teamId);
        return PackedResponse.success(response, "");
    }
}
