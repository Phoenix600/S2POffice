package com.s2p.util;

import com.s2p.dto.AssessmentResultDTO;
import com.s2p.model.AssessmentResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssessmentResultUtility {
    public abstract AssessmentResult toAssessmentResultEntity(AssessmentResultDTO resultDTO);
    public abstract AssessmentResultDTO toAssessmentResultDto(AssessmentResult result);
}
