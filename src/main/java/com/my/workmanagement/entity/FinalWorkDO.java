package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "final_work")
public class FinalWorkDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fworkId;
    @Column(nullable = false, unique = true)
    private String fworkName;
    private String fworkDescription;
    @Column(unique = true)
    private int groupId;
    private int fworkScore;
    @CreationTimestamp
    private Timestamp timeUpload;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getFworkId() {
        return fworkId;
    }

    public void setFworkId(int fworkId) {
        this.fworkId = fworkId;
    }

    public String getFworkName() {
        return fworkName;
    }

    public void setFworkName(String fworkName) {
        this.fworkName = fworkName;
    }

    public String getFworkDescription() {
        return fworkDescription;
    }

    public void setFworkDescription(String fworkDescription) {
        this.fworkDescription = fworkDescription;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getFworkScore() {
        return fworkScore;
    }

    public void setFworkScore(int fworkScore) {
        this.fworkScore = fworkScore;
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
                "fworkId=" + fworkId +
                ", fworkName='" + fworkName + '\'' +
                ", fworkDescription='" + fworkDescription + '\'' +
                ", groupId=" + groupId +
                ", fworkScore=" + fworkScore +
                ", timeUpload=" + timeUpload +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static FinalWorkBuilder builder() {
        return new FinalWorkBuilder();
    }


    public static final class FinalWorkBuilder {
        private int fworkId;
        private String fworkName;
        private String fworkDescription;
        private int groupId;
        private int fworkScore;
        private Timestamp timeUpload;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private FinalWorkBuilder() {
        }

        public static FinalWorkBuilder aFinalWorkDO() {
            return new FinalWorkBuilder();
        }

        public FinalWorkBuilder withFworkId(int fworkId) {
            this.fworkId = fworkId;
            return this;
        }

        public FinalWorkBuilder withFworkName(String fworkName) {
            this.fworkName = fworkName;
            return this;
        }

        public FinalWorkBuilder withFworkDescription(String fworkDescription) {
            this.fworkDescription = fworkDescription;
            return this;
        }

        public FinalWorkBuilder withGroupId(int groupId) {
            this.groupId = groupId;
            return this;
        }

        public FinalWorkBuilder withFworkScore(int fworkScore) {
            this.fworkScore = fworkScore;
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
            finalWorkDO.setFworkId(fworkId);
            finalWorkDO.setFworkName(fworkName);
            finalWorkDO.setFworkDescription(fworkDescription);
            finalWorkDO.setGroupId(groupId);
            finalWorkDO.setFworkScore(fworkScore);
            finalWorkDO.setTimeUpload(timeUpload);
            finalWorkDO.setGmtCreate(gmtCreate);
            finalWorkDO.setGmtModified(gmtModified);
            return finalWorkDO;
        }
    }
}
