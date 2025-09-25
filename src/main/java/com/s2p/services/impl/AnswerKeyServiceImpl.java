package com.s2p.services.impl;

import com.s2p.dto.AnswerKeyDTO;
import com.s2p.model.AnswerKey;
import com.s2p.model.QuestionPaper;
import com.s2p.repository.AnswerKeyRepository;
import com.s2p.repository.QuestionPaperRepository;
import com.s2p.services.AnswerKeyService;
import com.s2p.util.AnswerKeyUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AnswerKeyServiceImpl implements AnswerKeyService {

    private final AnswerKeyRepository answerKeyRepository;
    private final QuestionPaperRepository questionPaperRepository;
    private final AnswerKeyUtility answerKeyUtility;

    public AnswerKeyServiceImpl(AnswerKeyRepository answerKeyRepository,
                                QuestionPaperRepository questionPaperRepository,
                                AnswerKeyUtility answerKeyUtility) {
        this.answerKeyRepository = answerKeyRepository;
        this.questionPaperRepository = questionPaperRepository;
        this.answerKeyUtility = answerKeyUtility;
    }

    @Override
    public AnswerKeyDTO createAnswerKey(AnswerKeyDTO answerKeyDTO)
    {
        AnswerKey answerKey = answerKeyUtility.toAnswerKeyEntity(answerKeyDTO);

        AnswerKey savedAnswerKey = answerKeyRepository.save(answerKey);

        return answerKeyUtility.toAnswerKeyDto(savedAnswerKey);
    }


    @Override
    public AnswerKeyDTO getAnswerKeyByQuestionPaperTitle(String questionPaperTitle) {
        QuestionPaper questionPaper = questionPaperRepository.findByTitle(questionPaperTitle)
                .orElseThrow(() -> new RuntimeException("QuestionPaper not found with title: " + questionPaperTitle));

        AnswerKey answerKey = (AnswerKey) answerKeyRepository.findByQuestionPaper(questionPaper)
                .orElseThrow(() -> new RuntimeException("AnswerKey not found for QuestionPaper: " + questionPaperTitle));

        return answerKeyUtility.toAnswerKeyDto(answerKey);
    }

    @Override
    public List<AnswerKeyDTO> getAllAnswerKeys() {
        List<AnswerKey> allAnswerKeys = answerKeyRepository.findAll();
        List<AnswerKeyDTO> result = new ArrayList<>();

        for (AnswerKey ak : allAnswerKeys) {
            result.add(answerKeyUtility.toAnswerKeyDto(ak));
        }

        return result;
    }

    @Override
    public AnswerKeyDTO updateAnswerKey(String questionPaperTitle, AnswerKeyDTO answerKeyDTO) {
        QuestionPaper questionPaper = questionPaperRepository.findByTitle(questionPaperTitle)
                .orElseThrow(() -> new RuntimeException("QuestionPaper not found with title: " + questionPaperTitle));

        AnswerKey existing = (AnswerKey) answerKeyRepository.findByQuestionPaper(questionPaper)
                .orElseThrow(() -> new RuntimeException("AnswerKey not found for QuestionPaper: " + questionPaperTitle));

        if (answerKeyDTO.getAnswers() != null) {
            existing.setAnswers(answerKeyDTO.getAnswers());
        }

        AnswerKey updated = answerKeyRepository.save(existing);
        return answerKeyUtility.toAnswerKeyDto(updated);
    }

    @Override
    public void deleteAnswerKey(String questionPaperTitle) {
        QuestionPaper questionPaper = questionPaperRepository.findByTitle(questionPaperTitle)
                .orElseThrow(() -> new RuntimeException("QuestionPaper not found with title: " + questionPaperTitle));

        AnswerKey existing = (AnswerKey) answerKeyRepository.findByQuestionPaper(questionPaper)
                .orElseThrow(() -> new RuntimeException("AnswerKey not found for QuestionPaper: " + questionPaperTitle));

        answerKeyRepository.delete(existing);
    }
}
