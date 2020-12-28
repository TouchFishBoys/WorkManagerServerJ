package com.my.workmanagement.payload.response.student;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

public class StudentInfoResponse {
    //学生id
    private Integer studentId;
    //学生姓名
    private String studentName;
    //学生学号
    private String studentNum;
    //学生班级
    private String studentClass;
    //已选课程id
    private List<Integer> selectedCourseId;

    private StudentInfoResponse() {

    }

    public StudentInfoResponse(
            Integer studentId
            , String studentName
            , String studentNum
            , String studentClass
            , List<Integer> selectedCourseId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentNum = studentNum;
        this.studentClass = studentClass;
        this.selectedCourseId = selectedCourseId;
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

    public List<Integer> getSelectedCourseId() {
        return selectedCourseId;
    }

    public void setSelectedCourseId(List<Integer> selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

    public final static class StudentInfoResponseBuilder {
        //学生id
        private Integer studentId;
        //学生姓名
        private String studentName;
        //学生学号
        private String studentNum;
        //学生班级
        private String studentClass;
        //已选课程id
        private List<Integer> selectedCourseId;

        public StudentInfoResponse.StudentInfoResponseBuilder aStudentInfoResponseBuilder() {
            return new StudentInfoResponseBuilder();
        }

        public StudentInfoResponse.StudentInfoResponseBuilder withStudentId(Integer studentId){
            this.studentId=studentId;
            return this;
        }
        public StudentInfoResponse.StudentInfoResponseBuilder withStudentName(String studentName){
            this.studentName=studentName;
            return this;
        }
        public StudentInfoResponse.StudentInfoResponseBuilder withStudentNum(String studentNum){
            this.studentNum=studentNum;
            return this;
        }
        public StudentInfoResponse.StudentInfoResponseBuilder withStudentClass(String studentClass){
            this.studentClass=studentClass;
            return this;
        }
        public StudentInfoResponse.StudentInfoResponseBuilder withSelectedCourseId(List<Integer> selectedCourseId){
            this.selectedCourseId=selectedCourseId;
            return this;
        }

        public StudentInfoResponse build(){
            StudentInfoResponse studentInfoResponse=new StudentInfoResponse();
            studentInfoResponse.setStudentId(this.studentId);
            studentInfoResponse.setStudentName(this.studentName);
            studentInfoResponse.setStudentNum(this.studentNum);
            studentInfoResponse.setStudentClass(this.studentClass);
            studentInfoResponse.setSelectedCourseId(this.selectedCourseId);
            return studentInfoResponse;
        }

    }
}
