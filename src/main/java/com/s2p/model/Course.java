package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * File Name: Course.java
 * Entity: Course
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description: Represents a course with details such as name, description, duration,
 *              and its associations with enquiries, batches, students, fees, and teachers.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Schema(description = "Entity representing a Course offered by the institute")
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the course", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID courseId;

    @Column(nullable = false)
    @Schema(description = "Name of the course", example = "Java Full Stack Development")
    private String courseName;

    @Column(nullable = false)
    @Schema(description = "Description of the course", example = "Covers Java, Spring Boot, Hibernate, React, and REST APIs.")
    private String description;

    @Column(nullable = false)
    @Schema(description = "Course duration in months", example = "6")
    private Byte courseDurationInMonths;

    @ManyToMany
    @Schema(description = "Set of enquiries linked to this course")
    private Set<Enquiry> enquirySet = new HashSet<>();

    @ManyToMany(mappedBy = "courseSet")
    @Schema(description = "Set of batches associated with this course")
    private Set<Batch> batches = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    @Schema(description = "Set of students enrolled in this course")
    private Set<StudentInformation> students = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Fee structures associated with this course")
    private Set<CourseFees> courseFeesSet = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "course_teachers",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @Schema(description = "Set of teachers assigned to this course")
    private Set<TeacherUsers> teachers = new HashSet<>();
}
