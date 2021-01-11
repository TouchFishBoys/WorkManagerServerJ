package com.my.workmanagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "student")
public class StudentDO {
    @Id
    @GeneratedValue
    @Column(length = 8)
    private Integer studentId;

    @Column(nullable = false)
    private String studentName;
    @Column(nullable = false, unique = true)
    private String studentNum;
    @Column(nullable = false)
    @JsonIgnore
    private String studentPassword;

    @Column(nullable = false)
    private String studentClass;

    @CreationTimestamp
    private Timestamp gmtCreate;
    @UpdateTimestamp
    private Timestamp gmtModified;

    public StudentDO() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer stuId) {
        this.studentId = stuId;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String stuNum) {
        this.studentNum = stuNum;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String stuName) {
        this.studentName = stuName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String stuPassword) {
        this.studentPassword = stuPassword;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String stuClass) {
        this.studentClass = stuClass;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "StudentDO{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
