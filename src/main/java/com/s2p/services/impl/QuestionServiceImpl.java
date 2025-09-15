package com.s2p.services.impl;

import com.s2p.dto.QuestionDTO;
import com.s2p.model.Question;
import com.s2p.model.QuestionPaper;
import com.s2p.repository.QuestionPaperRepository;
import com.s2p.repository.QuestionRepository;
import com.s2p.services.IQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class QuestionServiceImpl implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionPaperRepository questionPaperRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository,
                               QuestionPaperRepository questionPaperRepository) {
        this.questionRepository = questionRepository;
        this.questionPaperRepository = questionPaperRepository;
    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        Optional<QuestionPaper> qpOpt = questionPaperRepository.findById(questionDTO.getQuestionPaperId());
        if (!qpOpt.isPresent()) {
            throw new RuntimeException("QuestionPaper not found with id: " + questionDTO.getQuestionPaperId());
        }

        Question question = new Question();
        question.setQuestionText(questionDTO.getQuestionText());
        question.setOptionA(questionDTO.getOptionA());
        question.setOptionB(questionDTO.getOptionB());
        question.setOptionC(questionDTO.getOptionC());
        question.setOptionD(questionDTO.getOptionD());
        question.setQuestionPaper(qpOpt.get());

        Question saved = questionRepository.save(question);

        QuestionDTO dto = new QuestionDTO();
        dto.setQuestionId(saved.getQuestionId());
        dto.setQuestionText(saved.getQuestionText());
        dto.setOptionA(saved.getOptionA());
        dto.setOptionB(saved.getOptionB());
        dto.setOptionC(saved.getOptionC());
        dto.setOptionD(saved.getOptionD());
        dto.setQuestionPaperId(saved.getQuestionPaper().getQuestionPaperId());

        return dto;
    }

    @Override
    public QuestionDTO getQuestionByText(String questionText) {
        Optional<Question> qOpt = Optional.ofNullable(questionRepository.findByQuestionText(questionText));
        if (!qOpt.isPresent()) {
            throw new RuntimeException("Question not found with text: " + questionText);
        }

        Question q = qOpt.get();
        QuestionDTO dto = new QuestionDTO();
        dto.setQuestionId(q.getQuestionId());
        dto.setQuestionText(q.getQuestionText());
        dto.setOptionA(q.getOptionA());
        dto.setOptionB(q.getOptionB());
        dto.setOptionC(q.getOptionC());
        dto.setOptionD(q.getOptionD());
        dto.setQuestionPaperId(q.getQuestionPaper().getQuestionPaperId());

        return dto;
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        List<Question> allQuestions = questionRepository.findAll();
        List<QuestionDTO> result = new ArrayList<>();

        for (Question q : allQuestions) {
            QuestionDTO dto = new QuestionDTO();
            dto.setQuestionId(q.getQuestionId());
            dto.setQuestionText(q.getQuestionText());
            dto.setOptionA(q.getOptionA());
            dto.setOptionB(q.getOptionB());
            dto.setOptionC(q.getOptionC());
            dto.setOptionD(q.getOptionD());
            dto.setQuestionPaperId(q.getQuestionPaper().getQuestionPaperId());
            result.add(dto);
        }

        return result;
    }

    @Override
    public QuestionDTO updateQuestion(String questionText, QuestionDTO questionDTO) {
        Optional<Question> qOpt = Optional.ofNullable(questionRepository.findByQuestionText(questionText));
        if (!qOpt.isPresent()) {
            throw new RuntimeException("Question not found with text: " + questionText);
        }

        Question existing = qOpt.get();

        if (questionDTO.getQuestionText() != null) existing.setQuestionText(questionDTO.getQuestionText());
        if (questionDTO.getOptionA() != null) existing.setOptionA(questionDTO.getOptionA());
        if (questionDTO.getOptionB() != null) existing.setOptionB(questionDTO.getOptionB());
        if (questionDTO.getOptionC() != null) existing.setOptionC(questionDTO.getOptionC());
        if (questionDTO.getOptionD() != null) existing.setOptionD(questionDTO.getOptionD());
        if (questionDTO.getQuestionPaperId() != null) {
            Optional<QuestionPaper> qpOpt = questionPaperRepository.findById(questionDTO.getQuestionPaperId());
            if (!qpOpt.isPresent()) {
                throw new RuntimeException("QuestionPaper not found with id: " + questionDTO.getQuestionPaperId());
            }
            existing.setQuestionPaper(qpOpt.get());
        }

        Question updated = questionRepository.save(existing);

        QuestionDTO dto = new QuestionDTO();
        dto.setQuestionId(updated.getQuestionId());
        dto.setQuestionText(updated.getQuestionText());
        dto.setOptionA(updated.getOptionA());
        dto.setOptionB(updated.getOptionB());
        dto.setOptionC(updated.getOptionC());
        dto.setOptionD(updated.getOptionD());
        dto.setQuestionPaperId(updated.getQuestionPaper().getQuestionPaperId());

        return dto;
    }

    @Override
    public void deleteQuestion(String questionText) {
        Optional<Question> qOpt = Optional.ofNullable(questionRepository.findByQuestionText(questionText));
        if (!qOpt.isPresent()) {
            throw new RuntimeException("Question not found with text: " + questionText);
        }

        questionRepository.delete(qOpt.get());
    }
}
