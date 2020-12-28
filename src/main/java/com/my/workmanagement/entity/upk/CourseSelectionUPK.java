package com.my.workmanagement.entity.upk;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.StudentDO;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseSelectionUPK implements Serializable {
    private StudentDO student;
    private CourseDO course;

    public CourseSelectionUPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseSelectionUPK that = (CourseSelectionUPK) o;
        return student.equals(that.student) && course.equals(that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }
}
