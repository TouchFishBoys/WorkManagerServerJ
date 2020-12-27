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
    @JoinColumn(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private StudentDO stuId;
    /**
     * 选课
     */
    @JoinColumn(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private CourseDO courseId;
    /**
     * 小队
     */
    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private TeamDO teamId;

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
