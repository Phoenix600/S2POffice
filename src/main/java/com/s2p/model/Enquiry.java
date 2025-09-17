package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * File Name: Enquiry.java
 * Entity: Enquiry
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description: Represents a student enquiry including date, courses, and student information.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Schema(description = "Entity representing a student enquiry with courses and student details")
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the enquiry", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID enquiryId;

    @CreatedDate
    @Schema(description = "Date when the enquiry was created", example = "2025-08-19")
    private LocalDate enquiryDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
    @Schema(description = "Student information associated with this enquiry")
    private StudentInformation studentInformation;

    @ManyToMany
    @JoinTable(
            name = "enquiry_course",
            joinColumns = {@JoinColumn(name = "enquiry_id", referencedColumnName = "enquiryId")},
            inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "courseId")}
    )
    @Schema(description = "Set of courses that the student is enquiring about")
    private Set<Course> courseSet = new HashSet<>();
}
