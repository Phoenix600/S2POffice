package com.s2p.services;

import com.s2p.dto.AssessmentDTO;
import java.util.List;

public interface AssessmentService {
    public abstract AssessmentDTO createAssessment(AssessmentDTO assessmentDTO);
    public abstract AssessmentDTO getAssessmentByTitle(String title);
    public abstract List<AssessmentDTO> getAllAssessments();
    public abstract AssessmentDTO updateAssessment(String title, AssessmentDTO assessmentDTO);
    public abstract void deleteAssessment(String title);

    // Special
    public abstract List<AssessmentDTO> getAssessmentsByTopicName(String topicName);
}
