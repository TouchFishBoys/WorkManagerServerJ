package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.finalwork.SetFinalScoreRequest;
import com.my.workmanagement.payload.response.finalwork.FinalWorkInfoResponse;
import com.my.workmanagement.service.interfaces.FinalWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/final")
public class FinalWorkController {
    private static final Logger logger = LoggerFactory.getLogger(NormalWorkController.class);
    private final FinalWorkService finalWorkService;

    @Autowired
    public FinalWorkController(FinalWorkService finalWorkService) {
        this.finalWorkService = finalWorkService;
    }

    @GetMapping(value = "/{teamId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PackedResponse<FinalWorkInfoResponse>> getFinalWorkInfo(@PathVariable Integer teamId) throws IdNotFoundException {
        FinalWorkInfoResponse response = finalWorkService.getFinalWorkInfo(teamId);
        return PackedResponse.success(response, "");
    }

    @PostMapping(value = "/{finalId}/score", consumes = {MediaType.APPLICATION_JSON_VALUE})
    // @PreAuthorize("hasRole(T(com.my.workmanagement.model.ERole).TEACHER)")
    public ResponseEntity<PackedResponse<Void>> setFinalWorkScore(
            @PathVariable Integer finalId,
            @RequestBody SetFinalScoreRequest request
    ) throws IdNotFoundException {
        if (finalWorkService.setFinalWorkScore(finalId, request.getScore())) {
            return PackedResponse.success(null, "Success");
        } else {
            return PackedResponse.failure(null, "Score has been set", HttpStatus.CONFLICT);
        }
    }
}
