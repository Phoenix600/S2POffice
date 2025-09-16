package com.s2p.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EvaluationResultDTO {
    private String studentEmail;
    private String questionPaperCode;
    private int totalQuestions;
    private int correctAnswers;
    private List<Boolean> answerEvaluation; // true = correct, false = wrong

}
