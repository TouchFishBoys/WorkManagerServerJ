package com.my.workmanagement.payload.response;

public class CourseInfoResponse {
    // 课程 Id
    private Integer courseId;
    // 开课教师
    private String teacher;
    // 课程名称
    private String courseName;
    // 课程描述
    private String courseDescription;
    // 学生数量
    private Integer studentCount;
    //课程年份
    private Integer courseYear;


    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setTeacherId(String teacher) {
        this.teacher = teacher;
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

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Integer getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(Integer courseYear) {
        this.courseYear = courseYear;
    }

    public CourseInfoResponse() {
    }

    public CourseInfoResponse(Integer courseId, String teacher, String courseName,String courseDescription, Integer studentCount,Integer courseYear) {
        this.courseId = courseId;
        this.teacher = teacher;
        this.courseName = courseName;
        this.courseDescription=courseDescription;
        this.studentCount = studentCount;
        this.courseYear=courseYear;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getTeacherId() {
        return teacher;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public static final class CourseInfoResponseBuilder {
        // 课程 Id
        private Integer courseId;
        // 开课教师
        private String teacher;
        // 课程名称
        private String courseName;
        // 课程描述
        private String courseDescription;
        // 学生数量
        private Integer studentCount;
        // 课程年份
        private Integer courseYear;

        private CourseInfoResponseBuilder() {
        }

        public static CourseInfoResponseBuilder aCourseInfoResponse() {
            return new CourseInfoResponseBuilder();
        }

        public CourseInfoResponseBuilder withCourseId(Integer courseId) {
            this.courseId = courseId;
            return this;
        }

        public CourseInfoResponseBuilder withTeacher(String teacher) {
            this.teacher = teacher;
            return this;
        }

        public CourseInfoResponseBuilder withCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public CourseInfoResponseBuilder withCourseDescription(String courseDescription) {
            this.courseDescription=courseDescription;
            return this;
        }

        public CourseInfoResponseBuilder withStudentCount(Integer studentCount) {
            this.studentCount = studentCount;
            return this;
        }

        public CourseInfoResponseBuilder withCourseYear(Integer courseYear) {
            this.courseYear=courseYear;
            return this;
        }

        public CourseInfoResponse build() {
            return new CourseInfoResponse(courseId, teacher, courseName, courseDescription,studentCount,courseYear);
        }
    }
}
