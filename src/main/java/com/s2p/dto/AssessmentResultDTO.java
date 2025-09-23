package com.s2p.dto;

import com.s2p.model.Assessment;
import com.s2p.model.StudentInformation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing the Assessment Result of a Student")
public class AssessmentResultDTO {

    @Schema(description = "Unique ID of the Assessment Result", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID resultId;

    @Schema(description = "Marks obtained by the student", example = "85")
    private Integer obtainedMarks;

    @Schema(description = "Whether the student passed the assessment", example = "true")
    private Boolean passed;

    @Schema(description = "Student associated with this result")
    private StudentInformation student;

    @Schema(description = "Assessment associated with this result")
    private Assessment assessment;
}
