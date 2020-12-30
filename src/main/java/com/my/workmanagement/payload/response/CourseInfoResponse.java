package com.my.workmanagement.payload.response;

public class CourseInfoResponse {
    // 课程 Id
    private Integer courseId;
    // 开课教师
    private String teacherName;
    // 课程名称
    private String courseName;
    // 课程描述
    private String courseDescription;
    // 总数量
    private Integer totalCount;
    //完成数量
    private Integer finishCount;
    //课程年份
    private Integer courseYear;

    private Integer studentCount;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(Integer courseYear) {
        this.courseYear = courseYear;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }


    public static final class CourseInfoResponseBuilder {
        // 课程 Id
        private Integer courseId;
        // 开课教师
        private String teacherName;
        // 课程名称
        private String courseName;
        // 课程描述
        private String courseDescription;
        // 总数量
        private Integer totalCount;
        //完成数量
        private Integer finishCount;
        //课程年份
        private Integer courseYear;
        private Integer studentCount;

        private CourseInfoResponseBuilder() {
        }

        public static CourseInfoResponseBuilder aCourseInfoResponse() {
            return new CourseInfoResponseBuilder();
        }

        public CourseInfoResponseBuilder withCourseId(Integer courseId) {
            this.courseId = courseId;
            return this;
        }

        public CourseInfoResponseBuilder withTeacherName(String teacherName) {
            this.teacherName = teacherName;
            return this;
        }

        public CourseInfoResponseBuilder withCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public CourseInfoResponseBuilder withCourseDescription(String courseDescription) {
            this.courseDescription = courseDescription;
            return this;
        }

        public CourseInfoResponseBuilder withTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public CourseInfoResponseBuilder withFinishCount(Integer finishCount) {
            this.finishCount = finishCount;
            return this;
        }

        public CourseInfoResponseBuilder withCourseYear(Integer courseYear) {
            this.courseYear = courseYear;
            return this;
        }

        public CourseInfoResponseBuilder withStudentCount(Integer studentCount) {
            this.studentCount = studentCount;
            return this;
        }

        public CourseInfoResponse build() {
            CourseInfoResponse courseInfoResponse = new CourseInfoResponse();
            courseInfoResponse.setCourseId(courseId);
            courseInfoResponse.setTeacherName(teacherName);
            courseInfoResponse.setCourseName(courseName);
            courseInfoResponse.setCourseDescription(courseDescription);
            courseInfoResponse.setTotalCount(totalCount);
            courseInfoResponse.setFinishCount(finishCount);
            courseInfoResponse.setCourseYear(courseYear);
            courseInfoResponse.setStudentCount(studentCount);
            return courseInfoResponse;
        }
    }

    @Override
    public String toString() {
        return "CourseInfoResponse{" +
                "courseId=" + courseId +
                ", teacherName='" + teacherName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", totalCount=" + totalCount +
                ", finishCount=" + finishCount +
                ", courseYear=" + courseYear +
                ", studentCount=" + studentCount +
                '}';
    }
}
