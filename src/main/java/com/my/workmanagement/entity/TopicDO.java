package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "topic_task")
public class TopicDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topicId;
    @Column(nullable = false)
    private int courseId;
    @Column(nullable = false)
    private String topicName;
    private String topicDescription;
    @CreationTimestamp
    private Timestamp topicTimeStart;
    @CreationTimestamp
    private Timestamp topicTimeEnd;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
                ", courseId=" + courseId +
                ", topicName='" + topicName + '\'' +
                ", topicDescription='" + topicDescription + '\'' +
                ", topicTimeStart=" + topicTimeStart +
                ", topicTimeEnd=" + topicTimeEnd +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static TopicBuilder builder() {
        return new TopicBuilder();
    }


    public static final class TopicBuilder {
        private int topicId;
        private int courseId;
        private String topicName;
        private String topicDescription;
        private Timestamp topicTimeStart;
        private Timestamp topicTimeEnd;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private TopicBuilder() {
        }

        public static TopicBuilder aTopicDO() {
            return new TopicBuilder();
        }

        public TopicBuilder withTopicId(int topicId) {
            this.topicId = topicId;
            return this;
        }

        public TopicBuilder withCourseId(int courseId) {
            this.courseId = courseId;
            return this;
        }

        public TopicBuilder withTopicName(String topicName) {
            this.topicName = topicName;
            return this;
        }

        public TopicBuilder withTopicDescription(String topicDescription) {
            this.topicDescription = topicDescription;
            return this;
        }

        public TopicBuilder withTopicTimeStart(Timestamp topicTimeStart) {
            this.topicTimeStart = topicTimeStart;
            return this;
        }

        public TopicBuilder withTopicTimeEnd(Timestamp topicTimeEnd) {
            this.topicTimeEnd = topicTimeEnd;
            return this;
        }

        public TopicBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public TopicBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public TopicDO build() {
            TopicDO topicDO = new TopicDO();
            topicDO.setTopicId(topicId);
            topicDO.setCourseId(courseId);
            topicDO.setTopicName(topicName);
            topicDO.setTopicDescription(topicDescription);
            topicDO.setTopicTimeStart(topicTimeStart);
            topicDO.setTopicTimeEnd(topicTimeEnd);
            topicDO.setGmtCreate(gmtCreate);
            topicDO.setGmtModified(gmtModified);
            return topicDO;
        }
    }
}
