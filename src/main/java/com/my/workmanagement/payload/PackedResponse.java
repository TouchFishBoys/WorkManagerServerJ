package com.my.workmanagement.payload;

public class PackedResponse<T> {
    private final T data;

    public PackedResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
