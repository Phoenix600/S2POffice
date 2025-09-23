package com.s2p.dto;

import com.s2p.model.Course;
import com.s2p.model.CourseFeeStructure;
import com.s2p.model.StudentUsers;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing a Course Fee Installment Transaction")
public class CourseFeeInstallmentTransactionsDto {

    @Schema(description = "Unique ID of the transaction", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID courseFeeInstallmentTransactionsId;

    @Schema(description = "Amount paid in this transaction", example = "2000.0")
    private Double paidAmount;

    @Schema(description = "Course associated with this transaction")
    private Course course;

    @Schema(description = "Fee structure associated with this transaction")
    private CourseFeeStructure courseFeeStructure;

    @Schema(description = "Student user who made the transaction")
    private StudentUsers studentUsers;
}
