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
    @Schema(
            description = "Unique identifier for the answer key",
            example = "d290f1ee-6c54-4b01-90e6-d701748f0851"
    )
    private UUID answerKeyId;

    @ElementCollection
    @CollectionTable(
            name = "answer_key_mapping",
            joinColumns = @JoinColumn(name = "answer_key_id")
    )
    @MapKeyColumn(name = "question_number")
    @Column(name = "correct_option")
    @Schema(
            description = "Mapping of question numbers to their correct options. " +
                    "Key = Question number, Value = Correct answer option.",
            example = "{\"1\": \"A\", \"2\": \"C\", \"3\": \"B\"}"
    )
    private Map<Integer, String> answers = new HashMap<>();

    @OneToOne
    @JoinColumn(name = "question_paper_id", nullable = false)
    @Schema(
            description = "Associated Question Paper for which this answer key is defined"
    )
    private QuestionPaper questionPaper;
}
