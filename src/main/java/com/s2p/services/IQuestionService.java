package com.s2p.services;

import com.s2p.dto.QuestionDTO;
import java.util.List;

public interface IQuestionService {
    public abstract QuestionDTO createQuestion(QuestionDTO questionDTO);
    public abstract QuestionDTO getQuestionByText(String questionText);
    public abstract List<QuestionDTO> getAllQuestions();
    public abstract QuestionDTO updateQuestion(String questionText, QuestionDTO questionDTO);
    public abstract void deleteQuestion(String questionText);
}
