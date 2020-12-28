package com.my.workmanagement.entity;

import com.my.workmanagement.entity.upk.CourseSelectionUPK;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course_selection")
@IdClass(CourseSelectionUPK.class)
public class CourseSelectionDO {
    /**
     * 学生
     */
    @Id
    @JoinColumn(nullable = false, name = "stu_id")
    @OneToOne(cascade = CascadeType.ALL)
    private StudentDO student;
    /**
     * 选课
     */
    @Id
    @JoinColumn(nullable = false, name = "course_id")
    @OneToOne(cascade = CascadeType.ALL)
    private CourseDO course;
    /**
     * 小组
     */
    @JoinColumn(name = "team_id")
    @OneToOne(cascade = CascadeType.ALL)
    private TeamDO team;

    /**
     * 答辩成绩
     */
    private Integer qaScore;
    /**
     * 总成绩
     */
    private Integer overallScore;

    @CreationTimestamp
    private Timestamp gmtCreate;
    @UpdateTimestamp
    private Timestamp gmtModified;

    public CourseSelectionDO() {
    }

    public StudentDO getStudent() {
        return student;
    }

    public void setStudent(StudentDO student) {
        this.student = student;
    }

    public CourseDO getCourse() {
        return course;
    }

    public void setCourse(CourseDO course) {
        this.course = course;
    }

    public TeamDO getTeam() {
        return team;
    }

    public void setTeam(TeamDO team) {
        this.team = team;
    }

    public Integer getQaScore() {
        return qaScore;
    }

    public void setQaScore(Integer qaScore) {
        this.qaScore = qaScore;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "CourseSelectionDO{" +
                "student=" + student +
                ", course=" + course +
                ", team=" + team +
                ", qaScore=" + qaScore +
                ", overallScore=" + overallScore +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
