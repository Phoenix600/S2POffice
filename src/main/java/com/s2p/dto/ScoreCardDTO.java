package com.s2p.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCardDTO {
    private String studentName;
    private String studentEmail;
    private String studentId;
    private LocalDateTime submittedAt;
    private Map<String, String> answerKey = new LinkedHashMap<>();
    private int totalQuestions;
    private int correctAnswers;
    private double scorePercentage;
}