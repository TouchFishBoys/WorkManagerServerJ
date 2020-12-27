package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "final_work")
public class FinalWorkDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fworkId;
    @Column(nullable = false, unique = true)
    private String fworkName;
    private String fworkDescription;

    @JoinColumn(unique = true)
    @OneToOne
    private TeamDO teamId;
    private Integer fworkScore;

    @CreationTimestamp
    private Timestamp timeUpload;

    @CreationTimestamp
    private Timestamp gmtCreate;
    @UpdateTimestamp
    private Timestamp gmtModified;

    public FinalWorkDO() {
    }

}
