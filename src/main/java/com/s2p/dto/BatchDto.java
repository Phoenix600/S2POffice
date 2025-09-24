package com.s2p.dto;

import com.s2p.model.Batch;
import com.s2p.model.Course;
import com.s2p.model.StudentInformation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing a Batch assigned to students")
public class BatchDto {

    @Schema(description = "Unique ID of the Batch", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID batchId;

    @Schema(description = "Name of the Batch", example = "Batch A")
    private String batchName;

    @Schema(description = "Start time of the Batch", example = "09:00")
    private LocalTime startTime;

    @Schema(description = "End time of the Batch", example = "11:00")
    private LocalTime endTime;

    @Schema(description = "Set of Courses assigned to this Batch")
    private Set<CourseDto> courseSet = new HashSet<>();

    @Schema(description = "Set of Students assigned to this Batch")
    private Set<StudentInformationDto> students = new HashSet<>();
}
