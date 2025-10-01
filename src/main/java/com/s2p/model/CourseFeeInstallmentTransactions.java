package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * File Name: CourseFeeInstallmentTransactions.java
 * Entity: CourseFeeInstallmentTransactions
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description: Represents transactions of fee installments paid by students for a course.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseFeeInstallmentTransactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseFeeInstallmentTransactionsId;

    private Double paidAmount;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )

    private Course course;

    @ManyToOne
    @JoinColumn(name = "course_fee_structure_id", referencedColumnName = "courseFeeStructureId")
    private CourseFeeStructure courseFeeStructure;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_information_id", referencedColumnName = "studentInformationId")
    private StudentInformation studentInformation;

    @ManyToOne
    @JoinColumn(name = "student_user_id", referencedColumnName = "studentUserId")
    private StudentUsers studentUsers;
}
