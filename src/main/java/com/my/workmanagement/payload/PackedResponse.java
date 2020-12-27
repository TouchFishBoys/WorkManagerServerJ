package com.my.workmanagement.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public final class PackedResponse<T> {
    private final T data;
    private final String message;
    private final ResponseResult result;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime timestamp;

    public static <T> ResponseEntity<PackedResponse<T>> badRequest(T data, String message) {
        return ResponseEntity.badRequest().body(new PackedResponse<>(data, message, ResponseResult.FAILED));
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
