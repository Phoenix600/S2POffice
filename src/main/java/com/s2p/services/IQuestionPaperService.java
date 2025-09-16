package com.s2p.services;

import com.s2p.dto.QuestionPaperDTO;
import java.util.List;

public interface IQuestionPaperService {
    public abstract QuestionPaperDTO createQuestionPaper(QuestionPaperDTO questionPaperDTO);
    public abstract QuestionPaperDTO getQuestionPaperByTitle(String title);
    public abstract List<QuestionPaperDTO> getAllQuestionPapers();
    public abstract QuestionPaperDTO updateQuestionPaper(String title, QuestionPaperDTO questionPaperDTO);
    public abstract void deleteQuestionPaper(String title);
}
