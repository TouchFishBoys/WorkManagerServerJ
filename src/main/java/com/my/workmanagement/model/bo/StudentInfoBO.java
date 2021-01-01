package com.my.workmanagement.model.bo;

import java.io.Serializable;
import java.util.List;

public class StudentInfoBO implements Serializable {
    // 学生 Id
    private Integer studentId;
    //学生姓名
    private String studentName;
    //学生学号
    private String studentNum;
    //学生班级（行政班）
    private String studentClass;

    public StudentInfoBO() {
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

    @Override
    public String toString() {
        return "StudentInfoBO{" +
                ", studentName='" + studentName + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", studentClass='" + studentClass + '\'' +
                '}';
    }

    public static final class StudentInfoBOBuilder {
        //学生姓名
        private String studentName;
        //学生学号
        private String studentNum;
        //学生班级（行政班）
        private String studentClass;

        private StudentInfoBOBuilder() {
        }

        public static StudentInfoBOBuilder aStudentInfoBO() {
            return new StudentInfoBOBuilder();
        }

        public StudentInfoBOBuilder withStudentName(String studentName) {
            this.studentName = studentName;
            return this;
        }

        public StudentInfoBOBuilder withStudentNum(String studentNum) {
            this.studentNum = studentNum;
            return this;
        }

        public StudentInfoBOBuilder withStudentClass(String studentClass) {
            this.studentClass = studentClass;
            return this;
        }

        public StudentInfoBO build() {
            StudentInfoBO studentInfoBO = new StudentInfoBO();
            studentInfoBO.setStudentName(studentName);
            studentInfoBO.setStudentNum(studentNum);
            studentInfoBO.setStudentClass(studentClass);
            return studentInfoBO;
        }
    }
}
