package com.s2p.services.impl;

import com.s2p.dto.QuestionDTO;
import com.s2p.services.IQuestionService;

import java.util.List;

public class QuestionServiceImpl implements IQuestionService
{
    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        return null;
    }

    @Override
    public QuestionDTO getQuestionByText(String questionText) {
        return null;
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        return List.of();
    }

    @Override
    public QuestionDTO updateQuestion(String questionText, QuestionDTO questionDTO) {
        return null;
    }

    @Override
    public void deleteQuestion(String questionText) {

    }
}
