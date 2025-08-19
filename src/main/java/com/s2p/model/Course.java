package com.s2p.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.experimental.PackagePrivate;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * File Name: Course.java
 * Entity: Course
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */

public class Course extends BaseEntity
{
	// Add Fields Here
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseId;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Byte courseDurationInMonths;

    // Build RelationShip Here
    private Set<Batch> batches = new HashSet<>();
}
