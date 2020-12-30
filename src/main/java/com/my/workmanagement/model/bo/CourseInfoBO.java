package com.my.workmanagement.model.bo;

import javax.persistence.criteria.CriteriaBuilder;

public class CourseInfoBO {
    private Integer courseId;
    private String courseName;
    private String courseTeacherName;
    private Integer finishCount;
    private Integer totalCount;
    private Integer courseYear;
    private String courseDescription;

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

    public Integer getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(Integer courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }


    public static final class CourseInfoBOBuilder {
        private Integer courseId;
        private String courseName;
        private String courseTeacherName;
        private Integer finishCount;
        private Integer totalCount;
        private Integer courseYear;
        private String courseDescription;

        private CourseInfoBOBuilder() {
        }

        public static CourseInfoBOBuilder aCourseInfoBO() {
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

        public CourseInfoBOBuilder withCourseYear(Integer courseYear) {
            this.courseYear = courseYear;
            return this;
        }

        public CourseInfoBOBuilder withCourseDescription(String courseDescription) {
            this.courseDescription = courseDescription;
            return this;
        }

        public CourseInfoBO build() {
            CourseInfoBO courseInfoBO = new CourseInfoBO();
            courseInfoBO.setCourseId(courseId);
            courseInfoBO.setCourseName(courseName);
            courseInfoBO.setCourseTeacherName(courseTeacherName);
            courseInfoBO.setFinishCount(finishCount);
            courseInfoBO.setTotalCount(totalCount);
            courseInfoBO.setCourseYear(courseYear);
            courseInfoBO.setCourseDescription(courseDescription);
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
                ", courseYear=" + courseYear +
                ", courseDescription='" + courseDescription + '\'' +
                '}';
    }
}
