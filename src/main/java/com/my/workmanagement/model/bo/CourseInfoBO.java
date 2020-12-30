package com.my.workmanagement.model.bo;

import javax.persistence.criteria.CriteriaBuilder;

public class CourseInfoBO {
    private Integer courseId;
    private String courseName;
    private String courseTeacherName;
    private Integer finishCount;
    private Integer totalCount;

    private CourseInfoBO() {
    }

    ;

    public CourseInfoBO(Integer courseId, String courseName, String courseTeacherName, Integer finishCount, Integer totalCount) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTeacherName = courseTeacherName;
        this.finishCount = finishCount;
        this.totalCount = totalCount;
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

    public String getCourseTeacherName() {
        return courseTeacherName;
    }

    public void setCourseTeacherName(String courseTeacherName) {
        this.courseTeacherName = courseTeacherName;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public static final class CourseInfoBOBuilder {
        private Integer courseId;
        private String courseName;
        private String courseTeacherName;
        private Integer finishCount;
        private Integer totalCount;

        private CourseInfoBOBuilder() {
        }

        ;

        public static CourseInfoBOBuilder aCourseInfoBOBuilder() {
            return new CourseInfoBOBuilder();
        }

        public CourseInfoBOBuilder withCourseId(Integer courseId) {
            this.courseId = courseId;
            return this;
        }

        public CourseInfoBOBuilder withCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public CourseInfoBOBuilder withCourseTeacherName(String courseTeacherName) {
            this.courseTeacherName = courseTeacherName;
            return this;
        }

        public CourseInfoBOBuilder withFinishCount(Integer finishCount) {
            this.finishCount = finishCount;
            return this;
        }

        public CourseInfoBOBuilder withTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public CourseInfoBO build() {
            CourseInfoBO courseInfoBO = new CourseInfoBO();
            courseInfoBO.setCourseName(this.courseName);
            courseInfoBO.setCourseId(this.courseId);
            courseInfoBO.setCourseTeacherName(this.courseTeacherName);
            courseInfoBO.setFinishCount(this.finishCount);
            courseInfoBO.setTotalCount(this.totalCount);
            return courseInfoBO;
        }

    }

    @Override
    public String toString() {
        return "CourseInfoBO{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseTeacherName='" + courseTeacherName + '\'' +
                ", finishCount=" + finishCount +
                ", totalCount=" + totalCount +
                '}';
    }
}
