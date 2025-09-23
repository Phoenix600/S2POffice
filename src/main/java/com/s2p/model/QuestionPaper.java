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
@Schema(description = "Entity representing a Question Paper containing multiple questions")
public class QuestionPaper extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier of the question paper", example = "a1b2c3d4-5678-90ab-cdef-1234567890ab")
    private UUID questionPaperId;

    @Column(nullable = false)
    @Schema(description = "Title of the question paper", example = "Midterm Exam - Java Basics")
    private String title;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    @Schema(description = "The topic to which this question paper belongs")
    private Topic topic;

    @OneToMany(mappedBy = "questionPaper", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Set of questions included in this question paper")
    private Set<Question> questions = new HashSet<>();

    @OneToOne(mappedBy = "questionPaper", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Answer key associated with this question paper")
    private AnswerKey answerKey;
}
