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
    private String courseDescription;
    private int courseYear;
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

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
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

    public static CourseBuilder builder() {
        return new CourseBuilder();
    }


    public static final class CourseBuilder {
        private int courseId;
        private String courseName;
        private int teacherId;
        private String courseDescription;
        private int courseYear;
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

        public CourseBuilder withCourseDescription(String courseDescription) {
            this.courseDescription = courseDescription;
            return this;
        }

        public CourseBuilder withCourseYear(int courseYear) {
            this.courseYear = courseYear;
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
            courseDO.setCourseDescription(courseDescription);
            courseDO.setCourseYear(courseYear);
            courseDO.setGmtCreate(gmtCreate);
            courseDO.setGmtModified(gmtModified);
            return courseDO;
        }
    }
}
