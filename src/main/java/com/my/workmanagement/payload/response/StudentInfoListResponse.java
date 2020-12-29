package com.my.workmanagement.payload.response;

import com.my.workmanagement.model.bo.StudentInfoBO;

import java.util.List;

public class StudentInfoListResponse {
    private String courseName;
    private List<StudentInfoBO> students;
}
