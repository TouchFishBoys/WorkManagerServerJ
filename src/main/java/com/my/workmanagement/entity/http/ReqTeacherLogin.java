package com.my.workmanagement.entity.http;

import javax.validation.constraints.NotEmpty;

public class ReqTeacherLogin {
    @NotEmpty(message = "工号不能为空")
    private String teacherNum;
    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
