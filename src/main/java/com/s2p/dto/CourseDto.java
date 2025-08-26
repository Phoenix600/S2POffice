package com.s2p.dto;

import com.s2p.model.Batch;
import com.s2p.model.CourseFees;
import com.s2p.model.Enquiry;
import com.s2p.model.StudentInformation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDto
{
    private UUID courseId;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Byte courseDurationInMonths;

    private Set<Enquiry> enquirySet = new HashSet<>();

    private Set<Batch> batches = new HashSet<>();

    private Set<StudentInformation> students = new HashSet<>();

    private Set<CourseFees> courseFeesSet = new HashSet<>();
}
