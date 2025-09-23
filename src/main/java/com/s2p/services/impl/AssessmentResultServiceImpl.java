package com.s2p.services.impl;

import com.s2p.dto.AssessmentResultDTO;
import com.s2p.model.Assessment;
import com.s2p.model.AssessmentResult;
import com.s2p.model.StudentInformation;
import com.s2p.repository.AssessmentRepository;
import com.s2p.repository.AssessmentResultRepository;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.services.AssessmentResultService;
import com.s2p.util.AssessmentResultUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository assessmentResultRepository;
    private final StudentInformationRepository studentInformationRepository;
    private final AssessmentRepository assessmentRepository;
    private final AssessmentResultUtility assessmentResultUtility;

    public AssessmentResultServiceImpl(
            AssessmentResultRepository assessmentResultRepository,
            StudentInformationRepository studentInformationRepository,
            AssessmentRepository assessmentRepository,
            AssessmentResultUtility assessmentResultUtility
    ) {
        this.assessmentResultRepository = assessmentResultRepository;
        this.studentInformationRepository = studentInformationRepository;
        this.assessmentRepository = assessmentRepository;
        this.assessmentResultUtility = assessmentResultUtility;
    }

    @Override
    public AssessmentResultDTO saveAssessmentResult(AssessmentResultDTO assessmentResultDto) {
        if (assessmentResultDto == null) {
            throw new RuntimeException("Invalid assessment result data");
        }

        StudentInformation student = studentInformationRepository.findByEmail(
                assessmentResultDto.getStudent().getEmail()
        ).orElseThrow(() -> new RuntimeException("Student not found with email: "
                + assessmentResultDto.getStudent().getEmail()));

        Assessment assessment = assessmentRepository.findById(
                assessmentResultDto.getAssessment().getAssessmentId()
        ).orElseThrow(() -> new RuntimeException("Assessment not found"));

        AssessmentResult entity = assessmentResultUtility.toAssessmentResultEntity(assessmentResultDto);
        entity.setStudent(student);
        entity.setAssessment(assessment);

        if (entity.getObtainedMarks() != null && assessment.getPassingMarks() != null) {
            entity.setPassed(entity.getObtainedMarks() >= assessment.getPassingMarks());
        }

        AssessmentResult savedEntity = assessmentResultRepository.save(entity);
        return assessmentResultUtility.toAssessmentResultDto(savedEntity);
    }

    @Override
    public AssessmentResultDTO updateAssessmentResultByStudentEmail(String studentEmail, AssessmentResultDTO assessmentResultDto) {
        StudentInformation student = studentInformationRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found with email: " + studentEmail));

        Assessment assessment = assessmentRepository.findById(
                assessmentResultDto.getAssessment().getAssessmentId()
        ).orElseThrow(() -> new RuntimeException("Assessment not found"));

        Optional<AssessmentResult> optionalResult = assessmentResultRepository.findByStudentAndAssessment(student, assessment);

        if (!optionalResult.isPresent()) {
            throw new RuntimeException("Assessment result not found for student: " + studentEmail);
        }

        AssessmentResult existing = optionalResult.get();
        existing.setObtainedMarks(assessmentResultDto.getObtainedMarks());

        if (assessment.getPassingMarks() != null && assessmentResultDto.getObtainedMarks() != null) {
            existing.setPassed(assessmentResultDto.getObtainedMarks() >= assessment.getPassingMarks());
        }

        AssessmentResult updated = assessmentResultRepository.save(existing);
        return assessmentResultUtility.toAssessmentResultDto(updated);
    }

    @Override
    public String removeAssessmentResultByStudentEmail(String studentEmail, AssessmentResultDTO assessmentResultDto) {
        StudentInformation student = studentInformationRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found with email: " + studentEmail));

        Assessment assessment = assessmentRepository.findById(
                assessmentResultDto.getAssessment().getAssessmentId()
        ).orElseThrow(() -> new RuntimeException("Assessment not found"));

        Optional<AssessmentResult> optionalResult = assessmentResultRepository.findByStudentAndAssessment(student, assessment);

        if (!optionalResult.isPresent()) {
            throw new RuntimeException("Assessment result not found for deletion");
        }

        assessmentResultRepository.delete(optionalResult.get());
        return "Assessment result deleted successfully for student " + studentEmail;
    }

    @Override
    public Optional<AssessmentResultDTO> findAssessmentResultByStudentEmail(String studentEmail, AssessmentResultDTO assessmentResultDto) {
        StudentInformation student = studentInformationRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found with email: " + studentEmail));

        Assessment assessment = assessmentRepository.findById(
                assessmentResultDto.getAssessment().getAssessmentId()
        ).orElseThrow(() -> new RuntimeException("Assessment not found"));

        Optional<AssessmentResult> result = assessmentResultRepository.findByStudentAndAssessment(student, assessment);

        if (result.isPresent()) {
            AssessmentResultDTO dto = assessmentResultUtility.toAssessmentResultDto(result.get());
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Override
    public List<AssessmentResultDTO> findAllAssessmentResultsByStudentEmail(String studentEmail) {
        StudentInformation student = studentInformationRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found with email: " + studentEmail));

        List<AssessmentResult> results = assessmentResultRepository.findByStudent(student);
        List<AssessmentResultDTO> dtoList = new ArrayList<>();

        for (AssessmentResult result : results) {
            AssessmentResultDTO dto = assessmentResultUtility.toAssessmentResultDto(result);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<AssessmentResultDTO> findAllAssessmentResults() {
        List<AssessmentResult> results = assessmentResultRepository.findAll();
        List<AssessmentResultDTO> dtoList = new ArrayList<>();

        for (AssessmentResult result : results) {
            AssessmentResultDTO dto = assessmentResultUtility.toAssessmentResultDto(result);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
