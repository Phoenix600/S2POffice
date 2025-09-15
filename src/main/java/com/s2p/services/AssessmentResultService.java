package com.s2p.services;

import com.s2p.dto.AssessmentResultDTO;
import java.util.List;

public interface AssessmentResultService {
    public abstract AssessmentResultDTO createAssessmentResult(AssessmentResultDTO resultDTO);
    public abstract AssessmentResultDTO getAssessmentResultByStudentName(String studentName);
    public abstract List<AssessmentResultDTO> getAllAssessmentResults();
    public abstract AssessmentResultDTO updateAssessmentResult(String studentName, AssessmentResultDTO resultDTO);
    public abstract void deleteAssessmentResult(String studentName);
}
