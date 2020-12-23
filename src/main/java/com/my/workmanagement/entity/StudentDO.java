package com.my.workmanagement.entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "student_info")
public class StudentDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    @Column(nullable = false, unique = true)
    private String studentNumber;
    @Column(nullable = false)
    private String studentName;
    @Column(nullable = false)
    private String secretKey;
    @Column(nullable = false)
    private String className;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int stuNumber) {
        this.studentId = stuNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentId) {
        this.studentNumber = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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
        return "StudentDO{" +
                "studentId=" + studentId +
                ", studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", className='" + className + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static final class StudentBuilder {
        private int stuNumber;
        private String studentId;
        private String studentName;
        private String secretKey;
        private String className;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private StudentBuilder() {
        }

        public static StudentBuilder aStudentDO() {
            return new StudentBuilder();
        }

        public StudentBuilder withStuNumber(int stuNumber) {
            this.stuNumber = stuNumber;
            return this;
        }

        public StudentBuilder withStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        public StudentBuilder withStudentName(String studentName) {
            this.studentName = studentName;
            return this;
        }

        public StudentBuilder withSecretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public StudentBuilder withClassName(String className) {
            this.className = className;
            return this;
        }

        public StudentBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public StudentBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public StudentDO build() {
            StudentDO studentDO = new StudentDO();
            studentDO.setStudentId(stuNumber);
            studentDO.setStudentNumber(studentId);
            studentDO.setStudentName(studentName);
            studentDO.setSecretKey(secretKey);
            studentDO.setClassName(className);
            studentDO.setGmtCreate(gmtCreate);
            studentDO.setGmtModified(gmtModified);
            return studentDO;
        }
    }
}
