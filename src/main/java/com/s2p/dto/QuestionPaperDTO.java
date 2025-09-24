package com.s2p.dto;

import com.s2p.model.AnswerKey;
import com.s2p.model.Question;
import com.s2p.model.Topic;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(name = "QuestionPaperDTO",
        description = "DTO representing a Question Paper. Contains metadata, topic, " +
                        "list of questions, and the associated answer key.")
public class QuestionPaperDTO {

        @Schema(
                description = "Unique identifier for the question paper",
                example = "9b4d2f98-2f3b-4f73-9a60-b3e5a6bdb9a1"
        )
        private UUID questionPaperId;

        @Schema(
                description = "Title of the question paper",
                example = "General Knowledge Test - 2025"
        )
        private String title;

        @Schema(
                description = "Topic associated with the question paper",
                implementation = TopicDTO.class
        )
        private TopicDTO topicDTO;

        @Schema(
                description = "Set of questions included in the question paper",
                implementation = QuestionDTO.class,
                example = "[{\"questionId\":\"111e4567-e89b-12d3-a456-426614174000\",\"questionText\":\"What is 2+2?\",\"optionA\":\"3\",\"optionB\":\"4\",\"optionC\":\"5\",\"optionD\":\"6\"}]"
        )
        private Set<QuestionDTO> questions = new HashSet<>();

        @Schema(
                description = "Answer key containing correct answers for the question paper",
                implementation = AnswerKeyDTO.class
        )
        private AnswerKeyDTO answerKeyDto;

}
