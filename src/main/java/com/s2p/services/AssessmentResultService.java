package com.s2p.services;

import com.s2p.dto.AssessmentResultDTO;
import java.util.List;
import java.util.Optional;

public interface AssessmentResultService
{
    AssessmentResultDTO saveAssessmentResult(AssessmentResultDTO assessmentResultDto);

    AssessmentResultDTO updateAssessmentResultByStudentEmail(String studentEmail, AssessmentResultDTO assessmentResultDto);

    String removeAssessmentResultByStudentEmail(String studentEmail, AssessmentResultDTO assessmentResultDto);

    Optional<AssessmentResultDTO> findAssessmentResultByStudentEmail(String studentEmail, AssessmentResultDTO assessmentResultDto);

    List<AssessmentResultDTO> findAllAssessmentResultsByStudentEmail(String studentEmail);

    List<AssessmentResultDTO> findAllAssessmentResults();
}
