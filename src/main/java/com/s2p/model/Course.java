package com.s2p.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Course extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseId;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Byte courseDurationInMonths;

    @ManyToMany(mappedBy = "courseSet")
    private Set<Batch> batches = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    private Set<StudentInformation> students = new HashSet<>();
}
