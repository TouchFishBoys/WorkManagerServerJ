package com.my.workmanagement.payload.response.student;

import com.my.workmanagement.model.bo.CourseInfoBO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

public class StudentInfoResponse {
    //学生姓名
    private String studentName;
    //学生学号
    private String studentNum;
    //学生班级
    private String studentClass;

    private String teamName;


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
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    public static final class StudentInfoResponseBuilder {
        //学生姓名
        private String studentName;
        //学生学号
        private String studentNum;
        //学生班级
        private String studentClass;
        private String teamName;

        private StudentInfoResponseBuilder() {
        }

        public static StudentInfoResponseBuilder aStudentInfoResponse() {
            return new StudentInfoResponseBuilder();
        }

        public StudentInfoResponseBuilder withStudentName(String studentName) {
            this.studentName = studentName;
            return this;
        }

        public StudentInfoResponseBuilder withStudentNum(String studentNum) {
            this.studentNum = studentNum;
            return this;
        }

        public StudentInfoResponseBuilder withStudentClass(String studentClass) {
            this.studentClass = studentClass;
            return this;
        }

        public StudentInfoResponseBuilder withTeamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public StudentInfoResponse build() {
            StudentInfoResponse studentInfoResponse = new StudentInfoResponse();
            studentInfoResponse.setStudentName(studentName);
            studentInfoResponse.setStudentNum(studentNum);
            studentInfoResponse.setStudentClass(studentClass);
            studentInfoResponse.setTeamName(teamName);
            return studentInfoResponse;
        }
    }

    @Override
    public String toString() {
        return "StudentInfoResponse{" +
                "studentName='" + studentName + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
