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

    @JoinColumn(nullable = false)
    @ManyToOne
    private StudentDO stuId;
    @JoinColumn(nullable = false)
    @OneToOne
    private TopicDO topicId;

    @JoinColumn(nullable = false, unique = true)
    private String nworkName;
    private Integer nworkScore;

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

    public StudentDO getStuId() {
        return stuId;
    }

    public void setStuId(StudentDO stuId) {
        this.stuId = stuId;
    }

    public TopicDO getTopicId() {
        return topicId;
    }

    public void setTopicId(TopicDO topicId) {
        this.topicId = topicId;
    }

    public String getNworkName() {
        return nworkName;
    }

    public void setNworkName(String nworkName) {
        this.nworkName = nworkName;
    }

    public Integer getNworkScore() {
        return nworkScore;
    }

    public void setNworkScore(Integer nworkScore) {
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
                ", topicId=" + topicId +
                ", nworkName='" + nworkName + '\'' +
                ", nworkScore=" + nworkScore +
                ", timeUpload=" + timeUpload +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
