package com.my.workmanagement.payload.response.student;

import com.my.workmanagement.model.bo.CourseInfoBO;

import java.io.Serializable;
import java.util.List;

public class CourseListResponse implements Serializable {
    private List<CourseInfoBO> courses;
    private Integer pageCount;

    public List<CourseInfoBO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseInfoBO> courses) {
        this.courses = courses;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
