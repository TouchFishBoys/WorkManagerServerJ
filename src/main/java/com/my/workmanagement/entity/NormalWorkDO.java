package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "normal_work")
public class NormalWorkDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nworkId;

    @JoinColumn(nullable = false)
    @ManyToOne
    private StudentDO stuId;
    @JoinColumn(nullable = false)
    @OneToOne
    private TopicDO topicId;

    @JoinColumn(nullable = false, unique = true)
    private String nworkName;
    private Integer nworkScore;

    private Timestamp timeUpload;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @UpdateTimestamp
    private Timestamp gmtModified;

    public NormalWorkDO() {
    }
}
