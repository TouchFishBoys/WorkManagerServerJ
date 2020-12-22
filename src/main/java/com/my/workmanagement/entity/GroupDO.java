package com.my.workmanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "group_task")
public class GroupDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    @Column(nullable = false, unique = true)
    private String groupName;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
        return "GroupDO{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static GroupBuilder builder() {
        return new GroupBuilder();
    }

    public static final class GroupBuilder {
        private int groupId;
        private String groupName;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private GroupBuilder() {
        }

        public static GroupBuilder aGroupDO() {
            return new GroupBuilder();
        }

        public GroupBuilder withGroupId(int groupId) {
            this.groupId = groupId;
            return this;
        }

        public GroupBuilder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public GroupBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public GroupBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public GroupDO build() {
            GroupDO groupDO = new GroupDO();
            groupDO.setGroupId(groupId);
            groupDO.setGroupName(groupName);
            groupDO.setGmtCreate(gmtCreate);
            groupDO.setGmtModified(gmtModified);
            return groupDO;
        }
    }
}
