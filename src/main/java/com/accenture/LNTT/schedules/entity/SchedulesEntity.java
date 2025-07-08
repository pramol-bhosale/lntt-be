package com.accenture.LNTT.schedules.entity;

import com.accenture.LNTT.activities.entity.ActivityEntity;
import com.accenture.LNTT.common.util.StaticConstants;
import com.accenture.LNTT.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "schedules", schema = StaticConstants.DEFAULT_SCHEMA)
public class SchedulesEntity {
    @Id
    private String id;
    private Date fromDate;
    private Date toDate;
    private Time fromTime;
    private Time toTime;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private ActivityEntity activity;
    @CreationTimestamp
    private Instant createdOn;
    @UpdateTimestamp
    private Instant updatedOn;

    @PrePersist
    void prePersist() {
        this.id = UUID.randomUUID().toString();
    }
}
