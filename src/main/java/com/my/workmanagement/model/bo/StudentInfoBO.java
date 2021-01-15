package com.my.workmanagement.model.bo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class StudentInfoBO implements Serializable {
    // 学生 Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer studentId;
    //学生姓名
    private String studentName;
    //学生学号
    private String studentNum;
    //学生班级（行政班）
    private String studentClass;

    private String TeamName;

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

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public static final class StudentInfoBOBuilder {
        // 学生 Id
        private Integer studentId;
        //学生姓名
        private String studentName;
        //学生学号
        private String studentNum;
        //学生班级（行政班）
        private String studentClass;
        private String TeamName;

        private StudentInfoBOBuilder() {
        }

        public static StudentInfoBOBuilder aStudentInfoBO() {
            return new StudentInfoBOBuilder();
        }

        public StudentInfoBOBuilder withStudentId(Integer studentId) {
            this.studentId = studentId;
            return this;
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

        public StudentInfoBOBuilder withTeamName(String TeamName) {
            this.TeamName = TeamName;
            return this;
        }

        public StudentInfoBO build() {
            StudentInfoBO studentInfoBO = new StudentInfoBO();
            studentInfoBO.setStudentId(studentId);
            studentInfoBO.setStudentName(studentName);
            studentInfoBO.setStudentNum(studentNum);
            studentInfoBO.setStudentClass(studentClass);
            studentInfoBO.setTeamName(TeamName);
            return studentInfoBO;
        }
    }

    @Override
    public String toString() {
        return "StudentInfoBO{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", TeamName='" + TeamName + '\'' +
                '}';
    }
}
