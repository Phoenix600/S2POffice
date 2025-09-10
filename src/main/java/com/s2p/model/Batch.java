package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Schema(description = "Entity representing the Assigned Batch of Students")
public class Batch extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique Identifier for Batch")
    private UUID batchId;

    @Column(nullable = true)
    private String batchName;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToMany
    @JoinTable(
            name = "batch_courses",
            joinColumns = @JoinColumn(name = "batch_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courseSet = new HashSet<>();

    @ManyToMany(mappedBy = "batches")
    private Set<StudentInformation> students = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "batch_teacher",
            joinColumns = @JoinColumn(name = "batch_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<TeacherUsers> teacher = new HashSet<>();


}