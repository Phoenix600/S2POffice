package com.s2p.dto;

import com.s2p.model.*;
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
    public class StudentInformationDto
    {
        private UUID studentInformationId;

        private String firstName;

        private String lastName;

        @Email(message = "Enter Valid Email")
        private String email;

        private String collegeName;

        private String departName;

        private String semester;

        private String passingYear;

        @Column(nullable = true)
        private Boolean isGraduated;

        private Boolean isAdmitted;

        private Enquiry enquiry;

        private Set<Batch> batches = new HashSet<>();

        private Set<Course> courses = new HashSet<>();

        private CourseFeeStructure courseFeeStructure;
    }
