package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "teacher_info")
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
    @CreationTimestamp
    private Timestamp gmtModified;

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

    public static TeacherBuilder builder() {
        return new TeacherBuilder();
    }


    public static final class TeacherBuilder {
        private int teacherId;
        private String teacherNum;
        private String teacherName;
        private String teacherPassword;
        private String teacherTell;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private TeacherBuilder() {
        }

        public static TeacherBuilder aTeacherDO() {
            return new TeacherBuilder();
        }

        public TeacherBuilder withTeacherId(int teacherId) {
            this.teacherId = teacherId;
            return this;
        }

        public TeacherBuilder withTeacherNum(String teacherNum) {
            this.teacherNum = teacherNum;
            return this;
        }

        public TeacherBuilder withTeacherName(String teacherName) {
            this.teacherName = teacherName;
            return this;
        }

        public TeacherBuilder withTeacherPassword(String teacherPassword) {
            this.teacherPassword = teacherPassword;
            return this;
        }

        public TeacherBuilder withTeacherTell(String teacherTell) {
            this.teacherTell = teacherTell;
            return this;
        }

        public TeacherBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public TeacherBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public TeacherDO build() {
            TeacherDO teacherDO = new TeacherDO();
            teacherDO.setTeacherId(teacherId);
            teacherDO.setTeacherNum(teacherNum);
            teacherDO.setTeacherName(teacherName);
            teacherDO.setTeacherPassword(teacherPassword);
            teacherDO.setTeacherTell(teacherTell);
            teacherDO.setGmtCreate(gmtCreate);
            teacherDO.setGmtModified(gmtModified);
            return teacherDO;
        }
    }
}
