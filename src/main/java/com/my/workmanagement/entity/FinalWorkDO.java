package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "final_work")
public class FinalWorkDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int finalId;
    @Column(nullable = false, unique = true)
    private String workName;
    private String finalDescription;
    @Column(unique = true)
    private int groupId;
    private int finalScore;
    @CreationTimestamp
    private Timestamp timeUpload;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getFinalId() {
        return finalId;
    }

    public void setFinalId(int finalId) {
        this.finalId = finalId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getFinalDescription() {
        return finalDescription;
    }

    public void setFinalDescription(String finalDescription) {
        this.finalDescription = finalDescription;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
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
        return "FinalWorkDO{" +
                "finalId=" + finalId +
                ", workName='" + workName + '\'' +
                ", finalDescription='" + finalDescription + '\'' +
                ", groupId=" + groupId +
                ", finalScore=" + finalScore +
                ", timeUpload=" + timeUpload +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static FinalWorkBuilder builder() {
        return new FinalWorkBuilder();
    }


    public static final class FinalWorkBuilder {
        private int finalId;
        private String workName;
        private String finalDescription;
        private int groupId;
        private int finalScore;
        private Timestamp timeUpload;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private FinalWorkBuilder() {
        }

        public static FinalWorkBuilder aFinalWorkDO() {
            return new FinalWorkBuilder();
        }

        public FinalWorkBuilder withFinalId(int finalId) {
            this.finalId = finalId;
            return this;
        }

        public FinalWorkBuilder withWorkName(String workName) {
            this.workName = workName;
            return this;
        }

        public FinalWorkBuilder withFinalDescription(String finalDescription) {
            this.finalDescription = finalDescription;
            return this;
        }

        public FinalWorkBuilder withGroupId(int groupId) {
            this.groupId = groupId;
            return this;
        }

        public FinalWorkBuilder withFinalScore(int finalScore) {
            this.finalScore = finalScore;
            return this;
        }

        public FinalWorkBuilder withTimeUpload(Timestamp timeUpload) {
            this.timeUpload = timeUpload;
            return this;
        }

        public FinalWorkBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public FinalWorkBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public FinalWorkDO build() {
            FinalWorkDO finalWorkDO = new FinalWorkDO();
            finalWorkDO.setFinalId(finalId);
            finalWorkDO.setWorkName(workName);
            finalWorkDO.setFinalDescription(finalDescription);
            finalWorkDO.setGroupId(groupId);
            finalWorkDO.setFinalScore(finalScore);
            finalWorkDO.setTimeUpload(timeUpload);
            finalWorkDO.setGmtCreate(gmtCreate);
            finalWorkDO.setGmtModified(gmtModified);
            return finalWorkDO;
        }
    }
}
