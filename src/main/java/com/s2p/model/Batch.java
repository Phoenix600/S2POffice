package com.s2p.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * File Name: Batch.java
 * Entity: Batch
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */

public class Batch extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID batchId;

    @Column(nullable = true)
    private String batchName;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    // Build Relationship Here
    private Set<Course> courseSet = new HashSet<>();
}
