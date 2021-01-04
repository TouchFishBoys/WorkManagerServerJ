package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "topic")
public class TopicDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicId;
    @JoinColumn(name = "course_id", nullable = false)
    @OneToOne()
    private CourseDO course;

    @Column(nullable = false)
    private String topicName;
    private String topicDescription;

    private Timestamp topicTimeStart;
    private Timestamp topicTimeEnd;

    @CreationTimestamp
    private Timestamp gmtCreate;
    @UpdateTimestamp
    private Timestamp gmtModified;

    public TopicDO() {
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public CourseDO getCourse() {
        return course;
    }

    public void setCourse(CourseDO course) {
        this.course = course;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public Timestamp getTopicTimeStart() {
        return topicTimeStart;
    }

    public void setTopicTimeStart(Timestamp topicTimeStart) {
        this.topicTimeStart = topicTimeStart;
    }

    public Timestamp getTopicTimeEnd() {
        return topicTimeEnd;
    }

    public void setTopicTimeEnd(Timestamp topicTimeEnd) {
        this.topicTimeEnd = topicTimeEnd;
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
        return "TopicDO{" +
                "topicId=" + topicId +
                ", course=" + course +
                ", topicName='" + topicName + '\'' +
                ", topicDescription='" + topicDescription + '\'' +
                ", topicTimeStart=" + topicTimeStart +
                ", topicTimeEnd=" + topicTimeEnd +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
