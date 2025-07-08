package com.accenture.LNTT.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users", schema = "public")
public class UserEntity implements UserDetails {
    @Id
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;
    private boolean enabled;
    @CreationTimestamp
    private Instant createdOn;
    private Instant updatedOn;

    @PrePersist
    void prePersist() {
        this.id = UUID.randomUUID().toString();
        this.email = email.toLowerCase();
        this.enabled = true;
    }

    @PreUpdate
    void preUpdate() {
        this.updatedOn = Instant.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
