package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course_selection")
public class CourseSelectionDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer csId;

    /**
     * 学生
     */
    @JoinColumn(nullable = false, name = "stu_id")
    @OneToOne(cascade = CascadeType.ALL)
    private StudentDO stu;
    /**
     * 选课
     */
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

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    public StudentDO getStu() {
        return stu;
    }

    public void setStu(StudentDO stu) {
        this.stu = stu;
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
}
