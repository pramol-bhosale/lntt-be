package com.accenture.LNTT.activities.entity;

import com.accenture.LNTT.common.util.StaticConstants;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "activities", schema = StaticConstants.DEFAULT_SCHEMA)
public class ActivityEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private Instant createdOn;
    private Instant updatedOn;
}
