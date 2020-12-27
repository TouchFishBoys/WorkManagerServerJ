package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "teacher")
public class TeacherDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;
    @Column(nullable = false, unique = true)
    private String teacherNum;
    @Column(nullable = false)
    private String teacherName;
    @Column(nullable = false)
    private String teacherPassword;
    @Column(unique = true)
    private String teacherTell;

    @CreationTimestamp
    private Timestamp gmtCreate;
    @UpdateTimestamp
    private Timestamp gmtModified;

    public TeacherDO() {
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public String getTeacherTell() {
        return teacherTell;
    }

    public void setTeacherTell(String teacherTell) {
        this.teacherTell = teacherTell;
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
        return "TeacherDO{" +
                "teacherId=" + teacherId +
                ", teacherNum='" + teacherNum + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherPassword='" + teacherPassword + '\'' +
                ", teacherTell='" + teacherTell + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
