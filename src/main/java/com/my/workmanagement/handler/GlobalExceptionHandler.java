package com.my.workmanagement.handler;

import com.my.workmanagement.exception.BaseException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.payload.PackedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<PackedResponse<Void>> baseExceptionHandler(BaseException e) {
        return PackedResponse.failure(null, e.getMessage(), e.getStatus());
    }
}
