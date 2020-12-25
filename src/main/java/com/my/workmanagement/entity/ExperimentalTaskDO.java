package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "experiment_task")
public class ExperimentalTaskDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expId;
    @Column(nullable = false)
    private int stuNumber;
    @Column(nullable = false)
    private String topicId;
    @Column(nullable = false, unique = true)
    private String fileName;
    private int expScore;
    @CreationTimestamp
    private Timestamp timeUpload;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(int stuNumber) {
        this.stuNumber = stuNumber;
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

    public int getExpScore() {
        return expScore;
    }

    public void setExpScore(int expScore) {
        this.expScore = expScore;
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
        return "ExperimentalTaskDO{" +
                "expId=" + expId +
                ", stuNumber=" + stuNumber +
                ", topicId='" + topicId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", expScore=" + expScore +
                ", timeUpload=" + timeUpload +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static ExperimentalTaskBuilder builder() {
        return new ExperimentalTaskBuilder();
    }


    public static final class ExperimentalTaskBuilder {
        private int expId;
        private int stuNumber;
        private String topicId;
        private String fileName;
        private int expScore;
        private Timestamp timeUpload;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private ExperimentalTaskBuilder() {
        }

        public static ExperimentalTaskBuilder anExperimentalTaskDO() {
            return new ExperimentalTaskBuilder();
        }

        public ExperimentalTaskBuilder withExpId(int expId) {
            this.expId = expId;
            return this;
        }

        public ExperimentalTaskBuilder withStuNumber(int stuNumber) {
            this.stuNumber = stuNumber;
            return this;
        }

        public ExperimentalTaskBuilder withTopicId(String topicId) {
            this.topicId = topicId;
            return this;
        }

        public ExperimentalTaskBuilder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public ExperimentalTaskBuilder withExpScore(int expScore) {
            this.expScore = expScore;
            return this;
        }

        public ExperimentalTaskBuilder withTimeUpload(Timestamp timeUpload) {
            this.timeUpload = timeUpload;
            return this;
        }

        public ExperimentalTaskBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public ExperimentalTaskBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public ExperimentalTaskDO build() {
            ExperimentalTaskDO experimentalTaskDO = new ExperimentalTaskDO();
            experimentalTaskDO.setExpId(expId);
            experimentalTaskDO.setStuNumber(stuNumber);
            experimentalTaskDO.setTopicId(topicId);
            experimentalTaskDO.setFileName(fileName);
            experimentalTaskDO.setExpScore(expScore);
            experimentalTaskDO.setTimeUpload(timeUpload);
            experimentalTaskDO.setGmtCreate(gmtCreate);
            experimentalTaskDO.setGmtModified(gmtModified);
            return experimentalTaskDO;
        }
    }
}
