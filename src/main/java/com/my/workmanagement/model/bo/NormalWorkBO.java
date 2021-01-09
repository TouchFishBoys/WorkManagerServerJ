package com.my.workmanagement.model.bo;

import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.entity.TopicDO;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

public class NormalWorkBO {

    private int nworkId;

    private StudentDO student;

    private TopicDO topic;

    private String nworkName;

    private Integer nworkScore;

    private Timestamp timeUpload;

    public int getNworkId() {
        return nworkId;
    }

    public void setNworkId(int nworkId) {
        this.nworkId = nworkId;
    }

    public StudentDO getStudent() {
        return student;
    }

    public void setStudent(StudentDO student) {
        this.student = student;
    }

    public TopicDO getTopic() {
        return topic;
    }

    public void setTopic(TopicDO topic) {
        this.topic = topic;
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


    public static final class NormalWorkBOBuilder {
        private int nworkId;
        private StudentDO student;
        private TopicDO topic;
        private String nworkName;
        private Integer nworkScore;
        private Timestamp timeUpload;

        private NormalWorkBOBuilder() {
        }

        public static NormalWorkBOBuilder aNormalWorkBO() {
            return new NormalWorkBOBuilder();
        }

        public NormalWorkBOBuilder withNworkId(int nworkId) {
            this.nworkId = nworkId;
            return this;
        }

        public NormalWorkBOBuilder withStudent(StudentDO student) {
            this.student = student;
            return this;
        }

        public NormalWorkBOBuilder withTopic(TopicDO topic) {
            this.topic = topic;
            return this;
        }

        public NormalWorkBOBuilder withNworkName(String nworkName) {
            this.nworkName = nworkName;
            return this;
        }

        public NormalWorkBOBuilder withNworkScore(Integer nworkScore) {
            this.nworkScore = nworkScore;
            return this;
        }

        public NormalWorkBOBuilder withTimeUpload(Timestamp timeUpload) {
            this.timeUpload = timeUpload;
            return this;
        }

        public NormalWorkBO build() {
            NormalWorkBO normalWorkBO = new NormalWorkBO();
            normalWorkBO.setNworkId(nworkId);
            normalWorkBO.setStudent(student);
            normalWorkBO.setTopic(topic);
            normalWorkBO.setNworkName(nworkName);
            normalWorkBO.setNworkScore(nworkScore);
            normalWorkBO.setTimeUpload(timeUpload);
            return normalWorkBO;
        }
    }

    @Override
    public String toString() {
        return "NormalWorkBO{" +
                "nworkId=" + nworkId +
                ", student=" + student +
                ", topic=" + topic +
                ", nworkName='" + nworkName + '\'' +
                ", nworkScore=" + nworkScore +
                ", timeUpload=" + timeUpload +
                '}';
    }
}
