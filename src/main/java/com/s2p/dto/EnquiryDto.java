package com.s2p.dto;

import com.s2p.model.Course;
import com.s2p.model.StudentInformation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing an Enquiry made by a student")
public class EnquiryDto {

    @Schema(description = "Unique ID of the enquiry", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID enquiryId;

    @Schema(description = "Date when the enquiry was made")
    private LocalDate enquiryDate;

    @Schema(description = "Student who made the enquiry")
    private StudentInformation studentInformation;

    @Schema(description = "Set of courses the enquiry is related to")
    private Set<Course> courseSet = new HashSet<>();
}
