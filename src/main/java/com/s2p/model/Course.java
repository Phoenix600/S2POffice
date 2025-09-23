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

public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseId;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Byte courseDurationInMonths;

    @ManyToMany
    private Set<Enquiry> enquirySet = new HashSet<>();

    @ManyToMany(mappedBy = "courseSet")
    private Set<Batch> batches = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    private Set<StudentInformation> students = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseFees> courseFeesSet = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "course_teachers",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<TeacherUsers> teachers = new HashSet<>();
}
