package com.my.workmanagement.payload.response.student;

import com.my.workmanagement.model.bo.CourseInfoBO;

import java.io.Serializable;
import java.util.List;

public class CourseListResponse implements Serializable {
    private List<CourseInfoBO> courseInfoList;
    public CourseListResponse(){
    };
    public CourseListResponse(List<CourseInfoBO>courseInfoList){
        this.courseInfoList=courseInfoList;
    }

    public List<CourseInfoBO> getCourseInfoList() {
        return courseInfoList;
    }

    public void setCourseInfoList(List<CourseInfoBO> courseInfoList) {
        this.courseInfoList = courseInfoList;
    }
}
