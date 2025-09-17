package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing the Answer Key for a Question Paper")
public class AnswerKeyDTO {

    @Schema(description = "Unique ID of the Answer Key", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID answerKeyId;

    @Schema(description = "Map of question number to correct option", example = "{1:'A', 2:'C', 3:'B'}")
    private Map<Integer, String> answers;

    @Schema(description = "ID of the associated Question Paper", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID questionPaperId;
}
