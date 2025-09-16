package com.s2p.util;

import com.s2p.dto.AnswerKeyDTO;
import com.s2p.model.AnswerKey;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerKeyUtility {
    public abstract AnswerKey toAnswerKeyEntity(AnswerKeyDTO answerKeyDTO);
    public abstract AnswerKeyDTO toAnswerKeyDto(AnswerKey answerKey);
}
