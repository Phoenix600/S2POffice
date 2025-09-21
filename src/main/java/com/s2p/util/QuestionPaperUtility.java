package com.s2p.util;

import com.s2p.dto.QuestionPaperDTO;
import com.s2p.model.QuestionPaper;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface QuestionPaperUtility {
    public abstract QuestionPaper toQuestionPaperEntity(QuestionPaperDTO questionPaperDTO);
    public abstract QuestionPaperDTO toQuestionPaperDto(Optional<QuestionPaper> questionPaper);
}
