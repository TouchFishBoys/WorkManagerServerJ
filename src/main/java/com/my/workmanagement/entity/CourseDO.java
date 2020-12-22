package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course_info")
public class CourseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    @Column(nullable = false, unique = true)
    private String courseName;
    @Column(nullable = false)
    private int teacherId;
    private String description;
    private int year;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
                ", description='" + description + '\'' +
                ", year=" + year +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static CourseBuilder builder() {
        return new CourseBuilder();
    }

    public static final class CourseBuilder {
        private int courseId;
        private String courseName;
        private int teacherId;
        private String description;
        private int year;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private CourseBuilder() {
        }

        public static CourseBuilder aCourseDO() {
            return new CourseBuilder();
        }

        public CourseBuilder withCourseId(int courseId) {
            this.courseId = courseId;
            return this;
        }

        public CourseBuilder withCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public CourseBuilder withTeacherId(int teacherId) {
            this.teacherId = teacherId;
            return this;
        }

        public CourseBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CourseBuilder withYear(int year) {
            this.year = year;
            return this;
        }

        public CourseBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public CourseBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public CourseDO build() {
            CourseDO courseDO = new CourseDO();
            courseDO.setCourseId(courseId);
            courseDO.setCourseName(courseName);
            courseDO.setTeacherId(teacherId);
            courseDO.setDescription(description);
            courseDO.setYear(year);
            courseDO.setGmtCreate(gmtCreate);
            courseDO.setGmtModified(gmtModified);
            return courseDO;
        }
    }
}
