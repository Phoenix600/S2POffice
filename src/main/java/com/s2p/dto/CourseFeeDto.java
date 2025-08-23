package com.s2p.dto;

import com.s2p.master.AcademicYear;
import com.s2p.model.Course;
import com.s2p.model.CourseFeeStructure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseFeeDto
{
    private UUID courseFeesID;

    private Long transactionId;

    private Double courseFees;

    private CourseFeeStructure feeStructure;

    private AcademicYear academicYear;

    private Course course;
}
