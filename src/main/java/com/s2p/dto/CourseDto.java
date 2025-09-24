package com.s2p.dto;

import com.s2p.model.Batch;
import com.s2p.model.CourseFees;
import com.s2p.model.Enquiry;
import com.s2p.model.StudentInformation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "DTO representing a Course entity")
public class CourseDto {

    @Schema(description = "Unique ID of the Course", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID courseId;

    @Schema(description = "Name of the Course", example = "Java Full Stack Development")
    private String courseName;

    @Schema(description = "Description of the Course")
    private String description;

    @Schema(description = "Duration of the Course in months", example = "6")
    private Byte courseDurationInMonths;

    @Schema(description = "Set of Enquiries associated with this Course")
    private Set<EnquiryDto> enquirySet = new HashSet<>();

    @Schema(description = "Set of Batches associated with this Course")
    private Set<BatchDto> batches = new HashSet<>();

    @Schema(description = "Set of Students enrolled in this Course")
    private Set<StudentInformationDto> students = new HashSet<>();

    @Schema(description = "Set of Course Fees associated with this Course")
    private Set<CourseFeeDto> courseFeesSet = new HashSet<>();
}
