package com.my.workmanagement.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public final class PackedResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;
    private final String message;
    private final ResponseResult result;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime timestamp;

    public static <T> ResponseEntity<PackedResponse<T>> badRequest(T data, String message) {
        return ResponseEntity.badRequest().body(new PackedResponse<>(data, message, ResponseResult.FAILED));
    }

    public static <T> ResponseEntity<PackedResponse<T>> notFound(T data, String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new PackedResponse<>(data, message, ResponseResult.FAILED));
    }

    public static <T> ResponseEntity<PackedResponse<T>> failure(T data, String message, HttpStatus code) {
        return ResponseEntity.status(code)
                .body(new PackedResponse<>(data, message, ResponseResult.FAILED));
    }

    public static <T> ResponseEntity<PackedResponse<T>> conflict(T data, String message) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new PackedResponse<>(data, message, ResponseResult.FAILED));
    }

    public static <T> ResponseEntity<PackedResponse<T>> success(T data, String message) {
        return ResponseEntity.ok(new PackedResponse<>(data, message, ResponseResult.SUCCESS));
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResult getResult() {
        return result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    private PackedResponse(T data, String message, ResponseResult result) {
        this.data = data;
        this.message = message;
        this.result = result;
        timestamp = LocalDateTime.now();
    }

}
