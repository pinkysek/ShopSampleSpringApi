package com.springapi.shopsample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * BaseEntity is an abstract class that provides common properties and methods
 * for entities, such as id, createdOn, and updatedOn timestamps.
 * It implements the IIdentifiedEntity interface with Long as the identifier type.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements IdentifiedEntity<Long> {

    /**
     * The unique identifier for the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The timestamp when the entity was created.
     * This field is non-nullable and cannot be updated.
     */
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    /**
     * The timestamp when the entity was last updated.
     */
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    /**
     * Sets the createdOn timestamp to the current date and time before the entity is persisted.
     */
    @PrePersist
    protected void onCreate() {
        this.createdOn = LocalDateTime.now();
    }

    /**
     * Sets the updatedOn timestamp to the current date and time before the entity is updated.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = LocalDateTime.now();
    }

}
