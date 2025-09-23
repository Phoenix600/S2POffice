package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

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
 * Description: Represents an assigned batch of students with time slots, courses, and teachers.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Schema(description = "Entity representing a batch of students, linked to courses and teachers.")
public class Batch extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the batch", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID batchId;

    @Column(nullable = true)
    @Schema(description = "Name of the batch", example = "Batch A - Morning")
    private String batchName;

    @Column(nullable = false)
    @Schema(description = "Start time of the batch", example = "09:00:00")
    private LocalTime startTime;

    @Column(nullable = false)
    @Schema(description = "End time of the batch", example = "11:00:00")
    private LocalTime endTime;

    @ManyToMany
    @JoinTable(
            name = "batch_courses",
            joinColumns = @JoinColumn(name = "batch_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @Schema(description = "Set of courses assigned to this batch")
    private Set<Course> courseSet = new HashSet<>();

    @ManyToMany(mappedBy = "batches")
    @Schema(description = "Set of students enrolled in this batch")
    private Set<StudentInformation> students = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "batch_teacher",
            joinColumns = @JoinColumn(name = "batch_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @Schema(description = "Set of teachers associated with this batch")
    private Set<TeacherUsers> teacher = new HashSet<>();
}
