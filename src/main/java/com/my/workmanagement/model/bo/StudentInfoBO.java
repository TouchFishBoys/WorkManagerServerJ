package com.my.workmanagement.model.bo;

import java.util.List;

public class StudentInfoBO {
    //学生id
    private Integer studentId;
    //学生姓名
    private String studentName;
    //学生学号
    private String studentNum;
    //学生班级
    private String studentClass;

    public StudentInfoBO() {
    }

    public StudentInfoBO(
            Integer studentId
            , String studentName
            , String studentNum
            , String studentClass
    ) {
        this.studentId=studentId;
        this.studentName=studentName;
        this.studentNum=studentNum;
        this.studentClass=studentClass;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }



}
