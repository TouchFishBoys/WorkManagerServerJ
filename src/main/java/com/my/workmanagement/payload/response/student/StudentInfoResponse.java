package com.my.workmanagement.payload.response.student;

import com.my.workmanagement.model.bo.CourseInfoBO;
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
    //已选课程信息
    private List<CourseInfoBO> selectedCourseInfo;

    private StudentInfoResponse() {

    }

    public StudentInfoResponse(
            Integer studentId
            , String studentName
            , String studentNum
            , String studentClass
            ,List<CourseInfoBO> selectedCourseInfo) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentNum = studentNum;
        this.studentClass = studentClass;
        this.selectedCourseInfo=selectedCourseInfo;
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


    public List<CourseInfoBO> getSelectedCourseInfo() {
        return selectedCourseInfo;
    }

    public void setSelectedCourseInfo(List<CourseInfoBO> selectedCourseInfo) {
        this.selectedCourseInfo = selectedCourseInfo;
    }
    public static final class StudentInfoResponseBuilder {
        //学生id
        private Integer studentId;
        //学生姓名
        private String studentName;
        //学生学号
        private String studentNum;
        //学生班级
        private String studentClass;
        //已选课程信息
        private List<CourseInfoBO> selectedCourseInfo;

        private StudentInfoResponseBuilder() {
        }

        public static StudentInfoResponse.StudentInfoResponseBuilder aStudentInfoResponse() {
            return new StudentInfoResponseBuilder();
        }

        public StudentInfoResponse.StudentInfoResponseBuilder withStudentId(Integer studentId) {
            this.studentId = studentId;
            return this;
        }

        public StudentInfoResponse.StudentInfoResponseBuilder withStudentName(String studentName) {
            this.studentName = studentName;
            return this;
        }

        public StudentInfoResponse.StudentInfoResponseBuilder withStudentNum(String studentNum) {
            this.studentNum = studentNum;
            return this;
        }

        public StudentInfoResponse.StudentInfoResponseBuilder withStudentClass(String studentClass) {
            this.studentClass = studentClass;
            return this;
        }

        public StudentInfoResponse.StudentInfoResponseBuilder withSelectedCourseInfo(List<CourseInfoBO> selectedCourseInfo) {
            this.selectedCourseInfo = selectedCourseInfo;
            return this;
        }

        public StudentInfoResponse build() {
            StudentInfoResponse studentInfoResponse = new StudentInfoResponse();
            studentInfoResponse.setStudentId(studentId);
            studentInfoResponse.setStudentName(studentName);
            studentInfoResponse.setStudentNum(studentNum);
            studentInfoResponse.setStudentClass(studentClass);
            studentInfoResponse.setSelectedCourseInfo(selectedCourseInfo);
            return studentInfoResponse;
        }
    }



}
