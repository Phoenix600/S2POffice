package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.AssessmentDTO;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.AssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    // --- CREATE ---
    //http://localhost:8080/api/v1/assessments/create
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<AssessmentDTO>> create(@RequestBody AssessmentDTO dto) {
        AssessmentDTO created = assessmentService.createAssessment(dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_SAVED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        created
                )
        );
    }

    // --- GET BY TITLE ---
    @GetMapping("/{title}")
    public ResponseEntity<ApiResponseDto<AssessmentDTO>> getByTitle(@PathVariable String title) {
        AssessmentDTO assessment = assessmentService.getAssessmentByTitle(title);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        assessment
                )
        );
    }

    // --- GET ALL ---
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<AssessmentDTO>>> getAll() {
        List<AssessmentDTO> allAssessments = assessmentService.getAllAssessments();
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        allAssessments
                )
        );
    }

    // --- UPDATE ---
    @PutMapping("/{title}")
    public ResponseEntity<ApiResponseDto<AssessmentDTO>> update(
            @PathVariable String title,
            @RequestBody AssessmentDTO dto) {
        AssessmentDTO updated = assessmentService.updateAssessment(title, dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    // --- DELETE ---
    @DeleteMapping("/{title}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable String title) {
        assessmentService.deleteAssessment(title);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_DELETED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        null
                )
        );
    }

    // --- GET BY TOPIC NAME ---
    @GetMapping("/topic/{topicName}")
    public ResponseEntity<ApiResponseDto<List<AssessmentDTO>>> getByTopicName(@PathVariable String topicName) {
        List<AssessmentDTO> assessments = assessmentService.getAssessmentsByTopicName(topicName);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        assessments.isEmpty()
                                ? EApiResponseMessage.DATA_NOT_FOUND.getMessage()
                                : EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        assessments
                )
        );
    }
}
