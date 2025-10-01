package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.*;
import com.s2p.model.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing detailed information about a Student")
public class StudentInformationDto {

    @Schema(description = "Unique ID of the student", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID studentInformationId;

    @Schema(description = "First name of the student", example = "John")
    private String firstName;

    @Schema(description = "Last name of the student", example = "Doe")
    private String lastName;

    @Email(message = "Enter Valid Email")
    @Schema(description = "Email address of the student", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Name of the college", example = "JD College of Engineering")
    private String collegeName;

    @Schema(description = "Department of the student", example = "Computer Science")
    private String departName;

    @Schema(description = "Current semester of the student", example = "6th")
    private String semester;

    @Schema(description = "Passing year of the student", example = "2025")
    private String passingYear;

    @Schema(description = "Indicates whether the student has graduated", example = "true")
    @Column(nullable = true)
    private Boolean isGraduated;

    @Schema(description = "Indicates whether the student is admitted", example = "true")
    private Boolean isAdmitted;

    @Schema(description = "Indicates whether the student has discontinued", example = "false")
    private Boolean isDiscontinued;

    @Schema(description = "Reason for discontinuation if applicable", example = "Personal reasons")
    private String reasonOfDiscontinue;

    @Schema(description = "Associated Enquiry of the student")
    private EnquiryDto enquiryDto;

    @Schema(description = "Set of batches the student belongs to")
    private Set<BatchDto> batches = new HashSet<>();

    @Schema(description = "Set of courses the student is enrolled in")
    private Set<CourseDto> courses = new HashSet<>();

    @Schema(description = "Associated Course Fee Structure for the student")
    private CourseFeeStructureDto courseFeeStructureDto;
}
