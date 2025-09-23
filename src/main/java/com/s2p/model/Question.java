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
@Schema(description = "Entity representing a Question belonging to a Question Paper")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier of the question", example = "c0a80123-4567-89ab-cdef-1234567890ab")
    private UUID questionId;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Schema(description = "Text of the question", example = "What is the capital of France?")
    private String questionText;

    @Column(nullable = false)
    @Schema(description = "Option A for the question", example = "Paris")
    private String optionA;

    @Column(nullable = false)
    @Schema(description = "Option B for the question", example = "London")
    private String optionB;

    @Column(nullable = false)
    @Schema(description = "Option C for the question", example = "Berlin")
    private String optionC;

    @Column(nullable = false)
    @Schema(description = "Option D for the question", example = "Madrid")
    private String optionD;

    @ManyToOne
    @JoinColumn(name = "question_paper_id", nullable = false)
    @Schema(description = "The Question Paper to which this question belongs")
    private QuestionPaper questionPaper;
}
