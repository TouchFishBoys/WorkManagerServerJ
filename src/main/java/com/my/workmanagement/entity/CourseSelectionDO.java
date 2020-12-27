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

}
