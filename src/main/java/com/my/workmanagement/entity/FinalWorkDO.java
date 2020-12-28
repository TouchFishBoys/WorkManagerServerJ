package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "final_work")
public class FinalWorkDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fworkId;
    @Column(nullable = false, unique = true)
    private String fworkName;
    private String fworkDescription;

    @JoinColumn(unique = true)
    @OneToOne
    private TeamDO teamId;

    private Integer documentScore;
    private Integer fworkScore;

    @CreationTimestamp
    private Timestamp timeUpload;

    @CreationTimestamp
    private Timestamp gmtCreate;
    @UpdateTimestamp
    private Timestamp gmtModified;

    public FinalWorkDO() {
    }

    public Integer getDocumentScore() {
        return documentScore;
    }

    public void setDocumentScore(Integer documentScore) {
        this.documentScore = documentScore;
    }

    public Integer getFworkId() {
        return fworkId;
    }

    public void setFworkId(Integer fworkId) {
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

    public TeamDO getTeamId() {
        return teamId;
    }

    public void setTeamId(TeamDO teamId) {
        this.teamId = teamId;
    }

    public Integer getFworkScore() {
        return fworkScore;
    }

    public void setFworkScore(Integer fworkScore) {
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
                ", teamId=" + teamId +
                ", documentScore=" + documentScore +
                ", fworkScore=" + fworkScore +
                ", timeUpload=" + timeUpload +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
