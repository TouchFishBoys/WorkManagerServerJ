package com.my.workmanagement.payload.response;

public class CourseInfoResponse {
    // 课程 Id
    private Integer courseId;
    // 开课教师
    private String teacher;
    // 课程名称
    private String courseName;
    // 学生数量
    private Integer studentCount;

    public CourseInfoResponse() {
    }

    public CourseInfoResponse(Integer courseId, String teacher, String courseName, Integer studentCount) {
        this.courseId = courseId;
        this.teacher = teacher;
        this.courseName = courseName;
        this.studentCount = studentCount;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getTeacher() {
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
        // 学生数量
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

        public CourseInfoResponseBuilder withTeacher(String teacher) {
            this.teacher = teacher;
            return this;
        }

        public CourseInfoResponseBuilder withCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public CourseInfoResponseBuilder withStudentCount(Integer studentCount) {
            this.studentCount = studentCount;
            return this;
        }

        public CourseInfoResponse build() {
            return new CourseInfoResponse(courseId, teacher, courseName, studentCount);
        }
    }
}
