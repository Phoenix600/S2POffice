package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing a Question Paper")
public class QuestionPaperDTO {

    @Schema(description = "Unique ID of the Question Paper", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID questionPaperId;

    @Schema(description = "Title of the Question Paper", example = "Midterm Exam - Physics")
    private String title;

    @Schema(description = "ID of the related Topic")
    private UUID topicId;

    @Schema(description = "Set of IDs of the questions in this paper")
    private Set<UUID> questionIds;

    @Schema(description = "ID of the related Answer Key")
    private UUID answerKeyId;
}
