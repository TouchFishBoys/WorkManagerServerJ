package com.my.workmanagement.entity.upk;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.StudentDO;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseSelectionUPK implements Serializable {
    @JoinColumn(nullable = false, name = "stu_id")
    @OneToOne(cascade = CascadeType.ALL)
    private StudentDO student;
    @JoinColumn(nullable = false, name = "course_id")
    @OneToOne(cascade = CascadeType.ALL)
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
