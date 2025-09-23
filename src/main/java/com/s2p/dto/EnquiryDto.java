package com.s2p.dto;

import com.s2p.model.Course;
import com.s2p.model.StudentInformation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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


    @Schema(
            description = "Unique identifier for the enquiry",
            example = "550e8400-e29b-41d4-a716-446655440000"
    )
    private UUID enquiryId;

    @Schema(
            description = "Date when the enquiry was made",
            example = "2025-09-24"
    )
    private LocalDate enquiryDate;

    @Schema(
            description = "Information about the student who made the enquiry",
            implementation = StudentInformation.class
    )
    private StudentInformation studentInformation;

    @Schema(
            description = "Set of courses the student enquired about",
            implementation = Course.class,
            example = "[{\"courseId\":\"101\",\"courseName\":\"Computer Science\"}, {\"courseId\":\"102\",\"courseName\":\"Mathematics\"}]"
    )
    private Set<CourseDto> courseSet = new HashSet<>();
}
