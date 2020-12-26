package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course_selection")
public class CourseSelectionDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int csId;
    @Column(nullable = false)
    private int stuId;
    @Column(nullable = false)
    private int courseId;
    @Column(nullable = false)
    private int groupId;
    private int qaScore;
    private int overallScore;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getCsId() {
        return csId;
    }

    public void setCsId(int csId) {
        this.csId = csId;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getQaScore() {
        return qaScore;
    }

    public void setQaScore(int qaScore) {
        this.qaScore = qaScore;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
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
        return "CourseSelectionDO{" +
                "csId=" + csId +
                ", stuId=" + stuId +
                ", courseId=" + courseId +
                ", groupId=" + groupId +
                ", qaScore=" + qaScore +
                ", overallScore=" + overallScore +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static CourseSelectionBuilder builder() {
        return new CourseSelectionBuilder();
    }


    public static final class CourseSelectionBuilder {
        private int csId;
        private int stuId;
        private int courseId;
        private int groupId;
        private int qaScore;
        private int overallScore;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private CourseSelectionBuilder() {
        }

        public static CourseSelectionBuilder aCourseSelectionDO() {
            return new CourseSelectionBuilder();
        }

        public CourseSelectionBuilder withCsId(int csId) {
            this.csId = csId;
            return this;
        }

        public CourseSelectionBuilder withStuId(int stuId) {
            this.stuId = stuId;
            return this;
        }

        public CourseSelectionBuilder withCourseId(int courseId) {
            this.courseId = courseId;
            return this;
        }

        public CourseSelectionBuilder withGroupId(int groupId) {
            this.groupId = groupId;
            return this;
        }

        public CourseSelectionBuilder withQaScore(int qaScore) {
            this.qaScore = qaScore;
            return this;
        }

        public CourseSelectionBuilder withOverallScore(int overallScore) {
            this.overallScore = overallScore;
            return this;
        }

        public CourseSelectionBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public CourseSelectionBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public CourseSelectionDO build() {
            CourseSelectionDO courseSelectionDO = new CourseSelectionDO();
            courseSelectionDO.setCsId(csId);
            courseSelectionDO.setStuId(stuId);
            courseSelectionDO.setCourseId(courseId);
            courseSelectionDO.setGroupId(groupId);
            courseSelectionDO.setQaScore(qaScore);
            courseSelectionDO.setOverallScore(overallScore);
            courseSelectionDO.setGmtCreate(gmtCreate);
            courseSelectionDO.setGmtModified(gmtModified);
            return courseSelectionDO;
        }
    }
}
