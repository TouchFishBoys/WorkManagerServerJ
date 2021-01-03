package com.my.workmanagement.payload.request;

import java.io.Serializable;

public class SingleValueRequest<T> implements Serializable {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
