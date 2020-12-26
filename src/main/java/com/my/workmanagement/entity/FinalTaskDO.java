package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "final_task")
public class FinalTaskDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer finalId;
    @Column(nullable = false, unique = true)
    private String taskName;
    private String description;
    @Column(unique = true)
    private int groupId;
    private int wordScore;
    @CreationTimestamp
    private Timestamp timeUpload;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public Integer getFinalId() {
        return finalId;
    }

    public void setFinalId(Integer finalId) {
        this.finalId = finalId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getWordScore() {
        return wordScore;
    }

    public void setWordScore(int word_score) {
        this.wordScore = word_score;
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
        return "FinalTaskDO{" +
                "finalId='" + finalId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", groupId=" + groupId +
                ", word_score=" + wordScore +
                ", timeUpload=" + timeUpload +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static FinalTaskBuilder builder() {
        return new FinalTaskBuilder();
    }


    public static final class FinalTaskBuilder {
        private Integer finalId;
        private String taskName;
        private String description;
        private int groupId;
        private int word_score;
        private Timestamp timeUpload;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private FinalTaskBuilder() {
        }

        public static FinalTaskBuilder aFinalTaskDO() {
            return new FinalTaskBuilder();
        }

        public FinalTaskBuilder withFinalId(Integer finalId) {
            this.finalId = finalId;
            return this;
        }

        public FinalTaskBuilder withTaskName(String taskName) {
            this.taskName = taskName;
            return this;
        }

        public FinalTaskBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public FinalTaskBuilder withGroupId(int groupId) {
            this.groupId = groupId;
            return this;
        }

        public FinalTaskBuilder withWord_score(int word_score) {
            this.word_score = word_score;
            return this;
        }

        public FinalTaskBuilder withTimeUpload(Timestamp timeUpload) {
            this.timeUpload = timeUpload;
            return this;
        }

        public FinalTaskBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public FinalTaskBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public FinalTaskDO build() {
            FinalTaskDO finalTaskDO = new FinalTaskDO();
            finalTaskDO.setFinalId(finalId);
            finalTaskDO.setTaskName(taskName);
            finalTaskDO.setDescription(description);
            finalTaskDO.setGroupId(groupId);
            finalTaskDO.setWordScore(word_score);
            finalTaskDO.setTimeUpload(timeUpload);
            finalTaskDO.setGmtCreate(gmtCreate);
            finalTaskDO.setGmtModified(gmtModified);
            return finalTaskDO;
        }
    }
}
