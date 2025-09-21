package com.s2p.util;

import com.s2p.dto.QuestionDTO;
import com.s2p.model.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionUtility {
    public abstract Question toQuestionEntity(QuestionDTO questionDTO);
    public abstract QuestionDTO toQuestionDto(Question question);
}
