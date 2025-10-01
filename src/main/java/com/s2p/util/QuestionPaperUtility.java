package com.s2p.util;

import com.s2p.dto.QuestionPaperDTO;
import com.s2p.model.QuestionPaper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionPaperUtility {
    public abstract QuestionPaper toQuestionPaperEntity(QuestionPaperDTO questionPaperDTO);
    public abstract QuestionPaperDTO toQuestionPaperDto(QuestionPaper questionPaper);
}
