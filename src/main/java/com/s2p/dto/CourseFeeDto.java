package com.s2p.dto;

import com.s2p.master.dto.AcademicYearDto;
import com.s2p.master.model.AcademicYear;
import com.s2p.model.Course;
import com.s2p.model.CourseFeeStructure;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing Course Fee details")
public class CourseFeeDto {

    @Schema(description = "Unique ID of the Course Fee", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID courseFeesID;

    @Schema(description = "Total Course Fee", example = "5000.0")
    private Double courseFees;

    @Schema(description = "Expected amount for the Course Fee", example = "5000.0")
    private Double amountExpected;

    @Schema(description = "Amount paid by the student", example = "2000.0")
    private Double amountPaid;

    @Schema(description = "Due date for the fee payment")
    private LocalDate dueDate;

    @Schema(description = "Course Fee Structure details associated with this fee")
    private CourseFeeStructureDto feeStructureDto;

    @Schema(description = "Academic year associated with this fee")
    private AcademicYearDto academicYearDto;

    @Schema(description = "Course associated with this fee")
    private CourseDto courseDto;
}
