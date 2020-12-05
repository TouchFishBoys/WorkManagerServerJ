package com.my.workmanagement.entity.http;

public class RespEntity {
    private int code;
    private String msg;
    private Object data;

    public RespEntity(RespCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public RespEntity(RespCode code, Object data) {
        this(code);
        this.data = data;
    }
}
