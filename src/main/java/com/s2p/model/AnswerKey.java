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
        name = "AnswerKey",
        description = "Entity that stores the correct answers for a given Question Paper."
)
public class AnswerKey extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID answerKeyId;

    @ElementCollection
    @CollectionTable(
            name = "answer_key_mapping",
            joinColumns = @JoinColumn(name = "answer_key_id")
    )
    @MapKeyColumn(name = "question_number")
    @Column(name = "correct_option")
    private Map<Integer, String> answers = new HashMap<>();

    @OneToOne
    @JoinColumn(name = "question_paper_id", nullable = false)
    private QuestionPaper questionPaper;
}
