package com.s2p.services.impl;

import com.s2p.dto.QuestionPaperDTO;
import com.s2p.services.IQuestionPaperService;

import java.util.List;

public class QuestionPaperServiceImpl implements IQuestionPaperService {
    @Override
    public QuestionPaperDTO createQuestionPaper(QuestionPaperDTO questionPaperDTO) {
        return null;
    }

    @Override
    public QuestionPaperDTO getQuestionPaperByTitle(String title) {
        return null;
    }

    @Override
    public List<QuestionPaperDTO> getAllQuestionPapers() {
        return List.of();
    }

    @Override
    public QuestionPaperDTO updateQuestionPaper(String title, QuestionPaperDTO questionPaperDTO) {
        return null;
    }

    @Override
    public void deleteQuestionPaper(String title) {

    }
}
