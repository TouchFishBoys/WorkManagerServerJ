package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course")
public class CourseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    @Column(nullable = false, unique = true)
    private String courseName;
    @JoinColumn(nullable = false)
    @OneToOne
    private TeacherDO teacher;

    private String courseDescription;
    private Integer courseYear;

    @CreationTimestamp
    private Timestamp gmtCreate;
    @UpdateTimestamp
    private Timestamp gmtModified;

    public CourseDO() {
    }
}
