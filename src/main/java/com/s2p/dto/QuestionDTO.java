package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing a Question")
public class QuestionDTO {

    @Schema(description = "Unique ID of the question", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID questionId;

    @Schema(description = "Text of the question", example = "What is the capital of India?")
    private String questionText;

    @Schema(description = "Option A", example = "Mumbai")
    private String optionA;

    @Schema(description = "Option B", example = "New Delhi")
    private String optionB;

    @Schema(description = "Option C", example = "Kolkata")
    private String optionC;

    @Schema(description = "Option D", example = "Chennai")
    private String optionD;

    @Schema(description = "ID of the related Question Paper")
    private UUID questionPaperId;
}
