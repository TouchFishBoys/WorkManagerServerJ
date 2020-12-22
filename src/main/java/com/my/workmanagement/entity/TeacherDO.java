package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "teacher_info")
public class TeacherDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherNumber;
    @Column(nullable = false, unique = true)
    private String teacherId;
    @Column(nullable = false)
    private String teacherName;
    @Column(nullable = false)
    private String secretKey;
    @Column(unique = true)
    private String phoneNumber;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(int teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
                "id=" + teacherNumber +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }


    public static TeacherBuilder builder() {
        return new TeacherBuilder();
    }


    public static final class TeacherBuilder {
        private int teacherNumber;
        private String teacherId;
        private String teacherName;
        private String secretKey;
        private String phoneNumber;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private TeacherBuilder() {
        }

        public static TeacherBuilder aTeacherDO() {
            return new TeacherBuilder();
        }

        public TeacherBuilder withTeacherNumber(int teacherNumber) {
            this.teacherNumber = teacherNumber;
            return this;
        }

        public TeacherBuilder withTeacherId(String teacherId) {
            this.teacherId = teacherId;
            return this;
        }

        public TeacherBuilder withTeacherName(String teacherName) {
            this.teacherName = teacherName;
            return this;
        }

        public TeacherBuilder withSecretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public TeacherBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
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
            teacherDO.setTeacherNumber(teacherNumber);
            teacherDO.setTeacherId(teacherId);
            teacherDO.setTeacherName(teacherName);
            teacherDO.setSecretKey(secretKey);
            teacherDO.setPhoneNumber(phoneNumber);
            teacherDO.setGmtCreate(gmtCreate);
            teacherDO.setGmtModified(gmtModified);
            return teacherDO;
        }
    }
}
