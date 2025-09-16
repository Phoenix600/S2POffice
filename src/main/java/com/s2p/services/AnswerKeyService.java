package com.s2p.services;

import com.s2p.dto.AnswerKeyDTO;
import java.util.List;

public interface AnswerKeyService {
    public abstract AnswerKeyDTO createAnswerKey(AnswerKeyDTO answerKeyDTO);
    public abstract AnswerKeyDTO getAnswerKeyByQuestionPaperTitle(String questionPaperTitle);
    public abstract List<AnswerKeyDTO> getAllAnswerKeys();
    public abstract AnswerKeyDTO updateAnswerKey(String questionPaperTitle, AnswerKeyDTO answerKeyDTO);
    public abstract void deleteAnswerKey(String questionPaperTitle);
}
