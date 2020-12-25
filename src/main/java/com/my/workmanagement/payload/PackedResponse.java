package com.my.workmanagement.payload;

import java.time.LocalDateTime;

public class PackedResponse<T> {
    private final T data;
    private final String message;
    private final ResponseResult result;
    private final LocalDateTime timestamp;

    private PackedResponse(T data, String message, ResponseResult result) {
        this.data = data;
        this.message = message;
        this.result = result;
        timestamp = LocalDateTime.now();
    }

    public static <T> PackedResponse<T> success(T data, String message) {
        return new PackedResponse<>(data, message, ResponseResult.SUCCESS);
    }

    public static <T> PackedResponse<T> failed(T data, String message) {
        return new PackedResponse<>(data, message, ResponseResult.FAILED);
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
}
