package com.s2p.services.impl;

import com.s2p.dto.AssessmentDTO;
import com.s2p.services.AssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    @Override
    public AssessmentDTO createAssessment(AssessmentDTO assessmentDTO) {
        return null;
    }

    @Override
    public AssessmentDTO getAssessmentByTitle(String title) {
        return null;
    }

    @Override
    public List<AssessmentDTO> getAllAssessments() {
        return List.of();
    }

    @Override
    public AssessmentDTO updateAssessment(String title, AssessmentDTO assessmentDTO) {
        return null;
    }

    @Override
    public void deleteAssessment(String title) {

    }

    @Override
    public List<AssessmentDTO> getAssessmentsByTopicName(String topicName) {
        return List.of();
    }
}
