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
}
