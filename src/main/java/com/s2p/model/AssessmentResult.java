package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Schema(
        name = "AssessmentResult",
        description = "Represents the result of a student's assessment attempt."
)
public class AssessmentResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(
            description = "Unique identifier for the assessment result",
            example = "123e4567-e89b-12d3-a456-426614174000"
    )
    private UUID resultId;

    @Schema(
            description = "Marks obtained by the student",
            example = "78"
    )
    private Integer obtainedMarks;

    @Schema(
            description = "Indicates whether the student passed the assessment",
            example = "true"
    )
    private Boolean passed;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @Schema(
            description = "The student who attempted the assessment"
    )
    private StudentInformation student;

    @OneToOne
    @JoinColumn(name = "assessment_id", nullable = false)
    @Schema(
            description = "The assessment for which this result belongs"
    )
    private Assessment assessment;
}
