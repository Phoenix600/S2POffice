package com.s2p.dto;

import com.s2p.model.Course;
import com.s2p.model.CourseFees;
import com.s2p.model.StudentInformation;
import com.s2p.model.StudentUsers;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing the structure of a Course Fee")
public class CourseFeeStructureDto {

    @Schema(description = "Unique ID of the fee structure", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID courseFeeStructureId;

    @Schema(description = "Down payment amount", example = "1000.0")
    private Double downPayment;

    @Schema(description = "Remaining amount to be paid", example = "4000.0")
    private Double remainingAmount;

    @Schema(description = "Indicates if a discount was given", example = "true")
    private Boolean isDiscountGiven;

    @Schema(description = "Discount factor applied", example = "0.1")
    private Float isDiscountFactor;

    @Schema(description = "Number of installments", example = "5")
    private Byte nInstallments;

    @Schema(description = "Number of remaining installments", example = "3")
    private Byte remainingInstallments;

    @Schema(description = "Course associated with this fee structure")
    private Course course;

    @Schema(description = "Course fee details linked to this structure")
    private CourseFees courseFees;

    @Schema(description = "Student information associated with this structure")
    private StudentInformation studentInformation;

    @Schema(description = "Student user associated with this structure")
    private StudentUsers studentUsers;
}
