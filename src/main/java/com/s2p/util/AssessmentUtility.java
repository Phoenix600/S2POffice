package com.s2p.util;

import com.s2p.dto.AssessmentDTO;
import com.s2p.model.Assessment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssessmentUtility {
    public abstract Assessment toAssessmentEntity(AssessmentDTO assessmentDTO);
    public abstract AssessmentDTO toAssessmentDto(Assessment assessment);
}
