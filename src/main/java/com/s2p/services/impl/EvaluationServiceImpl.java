package com.s2p.services.impl;

import com.s2p.dto.EvaluationRequestDTO;
import com.s2p.dto.EvaluationResultDTO;
import com.s2p.model.AnswerKey;
import com.s2p.model.QuestionPaper;
import com.s2p.repository.AnswerKeyRepository;
import com.s2p.repository.QuestionPaperRepository;
import com.s2p.services.EvaluationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    private final QuestionPaperRepository questionPaperRepository;
    private final AnswerKeyRepository answerKeyRepository;

    public EvaluationServiceImpl(QuestionPaperRepository questionPaperRepository,
                                 AnswerKeyRepository answerKeyRepository) {
        this.questionPaperRepository = questionPaperRepository;
        this.answerKeyRepository = answerKeyRepository;
    }

    @Override
    public EvaluationResultDTO evaluate(EvaluationRequestDTO request) {

        // 1. Get Question Paper using title
        QuestionPaper qp = questionPaperRepository.findByTitle(request.getTitle())
                .orElseThrow(() -> new RuntimeException("QuestionPaper not found with title: " + request.getTitle()));

        // 2. Get Answer Key for Question Paper
        AnswerKey answerKey = (AnswerKey) answerKeyRepository.findByQuestionPaper(qp)
                .orElseThrow(() -> new RuntimeException("AnswerKey not found for QuestionPaper"));

        List<String> correctAnswers = (List<String>) answerKey.getAnswers();   // assume List<String>
        List<String> studentAnswers = request.getStudentAnswers();

        // 3. Compare Answers
        int score = 0;
        List<Boolean> correctness = new ArrayList<>();

        for (int i = 0; i < correctAnswers.size(); i++) {
            String correct = correctAnswers.get(i);
            String student = (i < studentAnswers.size()) ? studentAnswers.get(i) : null;

            if (correct != null && correct.equalsIgnoreCase(student)) {
                score++;
                correctness.add(true);
            } else {
                correctness.add(false);
            }
        }

        // 4. Prepare Result DTO
        EvaluationResultDTO result = new EvaluationResultDTO();
        result.setStudentEmail(request.getStudentEmail());
        result.setQuestionPaperCode(request.getTitle());
        result.setTotalQuestions(correctAnswers.size());
        result.setCorrectAnswers(score);
        result.setAnswerEvaluation(correctness);

        return result;
    }
}
