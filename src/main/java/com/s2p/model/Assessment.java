package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Schema(
        name = "Assessment",
        description = "Represents an assessment created for a course/topic, containing questions and linked results."
)
public class Assessment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(
            description = "Unique identifier for the assessment",
            example = "550e8400-e29b-41d4-a716-446655440000"
    )
    private UUID assessmentId;

    @Column(nullable = false)
    @Schema(
            description = "Title of the assessment",
            example = "Java Basics Quiz"
    )
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Schema(
            description = "Detailed description of the assessment",
            example = "This assessment covers Java OOP concepts, data types, and collections."
    )
    private String description;

    @Schema(
            description = "Total marks allotted for the assessment",
            example = "100"
    )
    private Integer totalMarks;

    @Schema(
            description = "Passing marks required to clear the assessment",
            example = "40"
    )
    private Integer passingMarks;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @Schema(
            description = "The course to which this assessment belongs"
    )
    private Course course;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    @Schema(
            description = "The topic associated with this assessment"
    )
    private Topic topic;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_paper_id", nullable = false)
    @Schema(
            description = "The question paper assigned to this assessment"
    )
    private QuestionPaper questionPaper;

    @OneToOne(mappedBy = "assessment", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(
            description = "The result generated after the assessment is attempted"
    )
    private AssessmentResult result;
}
