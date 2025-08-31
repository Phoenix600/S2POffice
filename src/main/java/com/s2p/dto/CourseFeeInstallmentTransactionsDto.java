package com.s2p.dto;

import com.s2p.model.Course;
import com.s2p.model.CourseFeeStructure;
import com.s2p.model.StudentUsers;
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
public class CourseFeeInstallmentTransactionsDto
{
    private UUID courseFeeInstallmentTransactionsId;

    private Double paidAmount;

    private Course course;

    private CourseFeeStructure courseFeeStructure;

    private StudentUsers studentUsers;

}
