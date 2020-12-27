package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "normal_work")
public class NormalWorkDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nworkId;
    @Column(nullable = false)
    private int stuId;
    @Column(nullable = false)
    private String topicId;
    @Column(nullable = false, unique = true)
    private String nworkName;
    private int nworkScore;

    @CreationTimestamp
    private Timestamp timeUpload;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @UpdateTimestamp
    private Timestamp gmtModified;

    public NormalWorkDO() {
    }

    public int getNworkId() {
        return nworkId;
    }

    public void setNworkId(int nworkId) {
        this.nworkId = nworkId;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getNworkName() {
        return nworkName;
    }

    public void setNworkName(String nworkName) {
        this.nworkName = nworkName;
    }

    public int getNworkScore() {
        return nworkScore;
    }

    public void setNworkScore(int nworkScore) {
        this.nworkScore = nworkScore;
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
                "nworkId=" + nworkId +
                ", stuId=" + stuId +
                ", topicId='" + topicId + '\'' +
                ", nworkName='" + nworkName + '\'' +
                ", nworkScore=" + nworkScore +
                ", timeUpload=" + timeUpload +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
