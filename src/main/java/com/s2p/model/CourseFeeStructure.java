package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Entity representing the fee structure of a course for a student, including installments and discounts")
public class CourseFeeStructure extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the course fee structure", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID courseFeeStructureId;

    @Schema(description = "Down payment amount for the course", example = "10000.0")
    private Double downPayment;

    @Schema(description = "Remaining amount after down payment", example = "40000.0")
    private Double remainingAmount;

    @Schema(description = "Indicates if a discount is given to the student", example = "true")
    private Boolean isDiscountGiven;

    @Schema(description = "Discount factor applied, if any", example = "0.1")
    private Float isDiscountFactor;

    @Schema(description = "Total number of installments planned for this course", example = "4")
    private Byte nInstallments;

    @Schema(description = "Remaining installments for the student", example = "3")
    private Byte remainingInstallments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    @Schema(description = "Course associated with this fee structure")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_fees_id", referencedColumnName = "courseFeesID")
    @Schema(description = "Course fees entity related to this structure")
    private CourseFees courseFees;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_user_id", referencedColumnName = "studentUserId")
    @Schema(description = "Student user for whom this fee structure is assigned")
    private StudentUsers studentUsers;

}