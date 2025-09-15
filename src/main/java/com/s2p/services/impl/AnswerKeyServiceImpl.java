package com.s2p.services.impl;

import com.s2p.dto.AnswerKeyDTO;
import com.s2p.model.AnswerKey;
import com.s2p.model.QuestionPaper;
import com.s2p.repository.AnswerKeyRepository;
import com.s2p.repository.QuestionPaperRepository;
import com.s2p.services.AnswerKeyService;
import com.s2p.util.AnswerKeyUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class AnswerKeyServiceImpl implements AnswerKeyService {

    @Autowired
    private AnswerKeyRepository answerKeyRepository;

    @Autowired
    private QuestionPaperRepository questionPaperRepository;

    @Autowired
    private AnswerKeyUtility answerKeyUtility;

    @Override
    public AnswerKeyDTO createAnswerKey(AnswerKeyDTO dto) {
        AnswerKey answerKey = answerKeyUtility.toAnswerKeyEntity(dto);

        if (dto.getQuestionPaperId() != null) {
            QuestionPaper qp = questionPaperRepository.findById(dto.getQuestionPaperId()).orElse(null);
            if (qp != null) answerKey.setQuestionPaper(qp);
        }

        AnswerKey saved = answerKeyRepository.save(answerKey);
        return answerKeyUtility.toAnswerKeyDto(saved);
    }

    @Override
    public AnswerKeyDTO getAnswerKeyByQuestionPaperTitle(String questionPaperTitle) {
        Optional<QuestionPaper> qp = questionPaperRepository.findByTitle(questionPaperTitle);
        if (qp != null && qp.get() != null) {
            return answerKeyUtility.toAnswerKeyDto(qp.get().getAnswerKey());
        }
        return null;
    }

    @Override
    public List<AnswerKeyDTO> getAllAnswerKeys() {
        List<AnswerKey> list = answerKeyRepository.findAll();
        List<AnswerKeyDTO> dtos = new ArrayList<>();
        for (AnswerKey ak : list) dtos.add(answerKeyUtility.toAnswerKeyDto(ak));
        return dtos;
    }

    @Override
    public AnswerKeyDTO updateAnswerKey(String questionPaperTitle, AnswerKeyDTO dto) {
        Optional<QuestionPaper> qp = questionPaperRepository.findByTitle(questionPaperTitle);
        if (qp != null && qp.get() != null) {
            AnswerKey answerKey = qp.get().getAnswerKey();
            answerKey.setAnswers(dto.getAnswers());

            AnswerKey updated = answerKeyRepository.save(answerKey);
            return answerKeyUtility.toAnswerKeyDto(updated);
        }
        return null;
    }

    @Override
    public void deleteAnswerKey(String questionPaperTitle) {
        Optional<QuestionPaper> qp = questionPaperRepository.findByTitle(questionPaperTitle);
        if (qp != null && qp.get() != null) {
            answerKeyRepository.delete(qp.get().getAnswerKey());
        }
    }
}
