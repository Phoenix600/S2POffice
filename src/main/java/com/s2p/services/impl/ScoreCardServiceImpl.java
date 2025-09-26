package com.s2p.services.impl;

import com.s2p.dto.EvaluationRequestDTO;
import com.s2p.dto.EvaluationResultDTO;
import com.s2p.dto.ScoreCardDTO;
import com.s2p.services.EvaluationService;
import com.s2p.services.IScoreCardService;
import com.s2p.services.IStudentInformationService;
import com.s2p.model.StudentInformation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Service
public class ScoreCardServiceImpl implements IScoreCardService {

    private final EvaluationService evaluationService;
    private final IStudentInformationService studentService;

    public ScoreCardServiceImpl(EvaluationService evaluationService,
                                IStudentInformationService studentService) {
        this.evaluationService = evaluationService;
        this.studentService = studentService;
    }

    @Override
    public ScoreCardDTO generateScoreCard(EvaluationRequestDTO request) {

        // 1. Evaluate test
        EvaluationResultDTO evalResult = evaluationService.evaluate(request);

        // 2. Fetch student info
        var studentDto = studentService.getStudentByEmail(request.getStudentEmail()).get();

        // 3. Prepare answer key map
        LinkedHashMap<String, String> answerMap = new LinkedHashMap<>();
        for (int i = 0; i < evalResult.getTotalQuestions(); i++) {
            String studentAnswer = (i < request.getStudentAnswers().size()) ? request.getStudentAnswers().get(i) : "No Answer";
            String correctAnswer = evalResult.getAnswerEvaluation().get(i) ? studentAnswer : "Correct Answer: " + "Unknown"; // Optionally map correct answer if you have it
            answerMap.put("Q" + (i + 1), "Student Answer: " + studentAnswer + " | Result: " + (evalResult.getAnswerEvaluation().get(i) ? "Correct" : "Wrong"));
        }

        // 4. Create scorecard
        ScoreCardDTO scoreCard = new ScoreCardDTO();
        scoreCard.setStudentName(studentDto.getFirstName() + " " + studentDto.getLastName());
        scoreCard.setStudentEmail(studentDto.getEmail());
        scoreCard.setStudentId(studentDto.getStudentInformationId().toString());
        scoreCard.setSubmittedAt(LocalDateTime.now());
        scoreCard.setAnswerKey(answerMap);
        scoreCard.setTotalQuestions(evalResult.getTotalQuestions());
        scoreCard.setCorrectAnswers(evalResult.getCorrectAnswers());
        scoreCard.setScorePercentage((evalResult.getCorrectAnswers() * 100.0) / evalResult.getTotalQuestions());

        return scoreCard;
    }
}
