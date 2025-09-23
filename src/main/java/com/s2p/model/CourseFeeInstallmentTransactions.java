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
@Schema(description = "Entity representing fee installment transactions for a course")
public class CourseFeeInstallmentTransactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the installment transaction", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID courseFeeInstallmentTransactionsId;

    @Schema(description = "Amount paid in this installment", example = "5000.0")
    private Double paidAmount;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    @Schema(description = "Course for which the installment is paid")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "course_fee_structure_id", referencedColumnName = "courseFeeStructureId")
    @Schema(description = "Reference to the course fee structure")
    private CourseFeeStructure courseFeeStructure;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_information_id", referencedColumnName = "studentInformationId")
    @Schema(description = "Student information associated with this payment")
    private StudentInformation studentInformation;

    @ManyToOne
    @JoinColumn(name = "student_user_id", referencedColumnName = "studentUserId")
    @Schema(description = "Student user account associated with this payment")
    private StudentUsers studentUsers;
}
