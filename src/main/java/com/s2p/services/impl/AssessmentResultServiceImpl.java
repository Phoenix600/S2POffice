package com.s2p.services.impl;

import com.s2p.dto.AssessmentResultDTO;
import com.s2p.model.AssessmentResult;
import com.s2p.model.StudentInformation;
import com.s2p.model.Assessment;
import com.s2p.repository.AssessmentResultRepository;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.repository.AssessmentRepository;
import com.s2p.services.AssessmentResultService;
import com.s2p.util.AssessmentResultUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    @Autowired
    private AssessmentResultRepository resultRepository;

    @Autowired
    private StudentInformationRepository studentRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private AssessmentResultUtility resultUtility;

    @Override
    public AssessmentResultDTO createAssessmentResult(AssessmentResultDTO dto) {
        AssessmentResult result = resultUtility.toAssessmentResultEntity(dto);

        if (dto.getStudentId() != null) {
            StudentInformation student = studentRepository.findById(dto.getStudentId()).orElse(null);
            if (student != null) result.setStudent(student);
        }

        if (dto.getAssessmentId() != null) {
            Assessment assessment = assessmentRepository.findById(dto.getAssessmentId()).orElse(null);
            if (assessment != null) result.setAssessment(assessment);
        }

        AssessmentResult saved = resultRepository.save(result);
        return resultUtility.toAssessmentResultDto(saved);
    }

    @Override
    public AssessmentResultDTO getAssessmentResultByStudentName(String studentName) {
        StudentInformation student = studentRepository.findByStudentName(studentName);
        if (student != null) {
            List<AssessmentResult> results = resultRepository.findByStudent(student);
            if (!results.isEmpty()) {
                // Return first result (or could be extended to return multiple)
                return resultUtility.toAssessmentResultDto(results.get(0));
            }
        }
        return null;
    }

    @Override
    public List<AssessmentResultDTO> getAllAssessmentResults() {
        List<AssessmentResult> list = resultRepository.findAll();
        List<AssessmentResultDTO> dtos = new ArrayList<>();
        for (AssessmentResult r : list) dtos.add(resultUtility.toAssessmentResultDto(r));
        return dtos;
    }

    @Override
    public AssessmentResultDTO updateAssessmentResult(String studentName, AssessmentResultDTO dto) {
        StudentInformation student = studentRepository.findByStudentName(studentName);
        if (student != null) {
            List<AssessmentResult> results = resultRepository.findByStudent(student);
            if (!results.isEmpty()) {
                AssessmentResult result = results.get(0); // Update first result
                result.setObtainedMarks(dto.getObtainedMarks());
                result.setPassed(dto.getPassed());

                if (dto.getAssessmentId() != null) {
                    Assessment assessment = assessmentRepository.findById(dto.getAssessmentId()).orElse(null);
                    if (assessment != null) result.setAssessment(assessment);
                }

                AssessmentResult updated = resultRepository.save(result);
                return resultUtility.toAssessmentResultDto(updated);
            }
        }
        return null;
    }

    @Override
    public void deleteAssessmentResult(String studentName) {
        StudentInformation student = studentRepository.findByStudentName(studentName);
        if (student != null) {
            List<AssessmentResult> results = resultRepository.findByStudent(student);
            for (AssessmentResult r : results) {
                resultRepository.delete(r);
            }
        }
    }
}
