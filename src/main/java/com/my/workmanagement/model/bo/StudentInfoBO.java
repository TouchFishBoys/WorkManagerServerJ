package com.my.workmanagement.model.bo;

import java.util.List;

public class StudentInfoBO {
    //学生姓名
    private String studentName;
    //学生学号
    private String studentNum;
    //学生班级（行政班）
    private String studentClass;

    public StudentInfoBO() {
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

    @Override
    public String toString() {
        return "StudentInfoBO{" +
                ", studentName='" + studentName + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", studentClass='" + studentClass + '\'' +
                '}';
    }
}
