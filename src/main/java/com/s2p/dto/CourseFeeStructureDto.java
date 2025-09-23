package com.s2p.dto;

import com.s2p.model.Course;
import com.s2p.model.CourseFees;
import com.s2p.model.StudentInformation;
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
public class CourseFeeStructureDto
{
    private UUID courseFeeStructureId;

    private Double downPayment;

    private Double remainingAmount;

    private Boolean isDiscountGiven;

    private Float isDiscountFactor;

    private Byte nInstallments;

    private Byte remainingInstallments;

    private Course course;

    private CourseFees courseFees;

    private StudentInformation studentInformation;

    private StudentUsers studentUsers;

}
