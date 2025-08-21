package com.s2p.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * File Name: StudentInformation.java
 * Entity: StudentInformation
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
@Table(name = "student_information")
public class StudentInformation extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID studentId;

    private String firstName;

    private String lastName;

    @Email(message = "Enter Valid Email")
    private String email;

    private String collegeName;

    private String degreeName;

    private String semester;

    private String passingYear;

    @Column(nullable = true)
    private Boolean isGraduated;


    @ManyToMany
    @JoinTable(
            name = "student_batches",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "batch_id")
    )
    private Set<Batch> batches = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
}
