package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "normal_work")
public class NormalWorkDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int normalId;
    @Column(nullable = false)
    private int studentId;
    @Column(nullable = false)
    private String topicId;
    @Column(nullable = false, unique = true)
    private String fileName;
    private int normalScore;
    @CreationTimestamp
    private Timestamp timeUpload;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getNormalId() {
        return normalId;
    }

    public void setNormalId(int normalId) {
        this.normalId = normalId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getNormalScore() {
        return normalScore;
    }

    public void setNormalScore(int normalScore) {
        this.normalScore = normalScore;
    }

    public Timestamp getTimeUpload() {
        return timeUpload;
    }

    public void setTimeUpload(Timestamp timeUpload) {
        this.timeUpload = timeUpload;
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
        return "NormalWorkDO{" +
                "normalId=" + normalId +
                ", studentId=" + studentId +
                ", topicId='" + topicId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", normalScore=" + normalScore +
                ", timeUpload=" + timeUpload +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static NormalWorkBuilder builder() {
        return new NormalWorkBuilder();
    }

    public static final class NormalWorkBuilder {
        private int normalId;
        private int studentId;
        private String topicId;
        private String fileName;
        private int normalScore;
        private Timestamp timeUpload;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private NormalWorkBuilder() {
        }

        public static NormalWorkBuilder aNormalWorkDO() {
            return new NormalWorkBuilder();
        }

        public NormalWorkBuilder withNormalId(int normalId) {
            this.normalId = normalId;
            return this;
        }

        public NormalWorkBuilder withStudentId(int studentId) {
            this.studentId = studentId;
            return this;
        }

        public NormalWorkBuilder withTopicId(String topicId) {
            this.topicId = topicId;
            return this;
        }

        public NormalWorkBuilder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public NormalWorkBuilder withNormalScore(int normalScore) {
            this.normalScore = normalScore;
            return this;
        }

        public NormalWorkBuilder withTimeUpload(Timestamp timeUpload) {
            this.timeUpload = timeUpload;
            return this;
        }

        public NormalWorkBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public NormalWorkBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public NormalWorkDO build() {
            NormalWorkDO normalWorkDO = new NormalWorkDO();
            normalWorkDO.setNormalId(normalId);
            normalWorkDO.setStudentId(studentId);
            normalWorkDO.setTopicId(topicId);
            normalWorkDO.setFileName(fileName);
            normalWorkDO.setNormalScore(normalScore);
            normalWorkDO.setTimeUpload(timeUpload);
            normalWorkDO.setGmtCreate(gmtCreate);
            normalWorkDO.setGmtModified(gmtModified);
            return normalWorkDO;
        }
    }
}
