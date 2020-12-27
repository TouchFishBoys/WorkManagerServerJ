package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course")
public class CourseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    @Column(nullable = false, unique = true)
    private String courseName;
    @Column(nullable = false)
    private Integer teacherId;
    private String courseDescription;
    private Integer courseYear;

    @CreationTimestamp
    private Timestamp gmtCreate;
    @UpdateTimestamp
    private Timestamp gmtModified;

    public CourseDO() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Integer getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(Integer courseYear) {
        this.courseYear = courseYear;
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
        return "CourseDO{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacherId=" + teacherId +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseYear=" + courseYear +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static final class CourseDOBuilder {
        private Integer courseId;
        private String courseName;
        private Integer teacherId;
        private String courseDescription;
        private Integer courseYear;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private CourseDOBuilder() {
        }

        public static CourseDOBuilder aCourseDO() {
            return new CourseDOBuilder();
        }

        public CourseDOBuilder withCourseId(Integer courseId) {
            this.courseId = courseId;
            return this;
        }

        public CourseDOBuilder withCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public CourseDOBuilder withTeacherId(Integer teacherId) {
            this.teacherId = teacherId;
            return this;
        }

        public CourseDOBuilder withCourseDescription(String courseDescription) {
            this.courseDescription = courseDescription;
            return this;
        }

        public CourseDOBuilder withCourseYear(Integer courseYear) {
            this.courseYear = courseYear;
            return this;
        }

        public CourseDOBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public CourseDOBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public CourseDO build() {
            CourseDO courseDO = new CourseDO();
            courseDO.setCourseId(courseId);
            courseDO.setCourseName(courseName);
            courseDO.setTeacherId(teacherId);
            courseDO.setCourseDescription(courseDescription);
            courseDO.setCourseYear(courseYear);
            courseDO.setGmtCreate(gmtCreate);
            courseDO.setGmtModified(gmtModified);
            return courseDO;
        }
    }
}
