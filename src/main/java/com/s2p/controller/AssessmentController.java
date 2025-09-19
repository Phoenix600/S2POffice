package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.AssessmentDTO;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.AssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assessments")
@Tag(name = "Assessment Controller", description = "APIs for managing Assessments and related data")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @Operation(
            summary = "Create a new Assessment",
            description = "This API creates a new assessment with the details provided in the request body."
    )
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

    @Operation(
            summary = "Get Assessment by Title",
            description = "This API retrieves an assessment using the title provided in the path variable."
    )
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

    @Operation(
            summary = "Get All Assessments",
            description = "This API retrieves a list of all assessments in the system."
    )
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

    @Operation(
            summary = "Update Assessment by Title",
            description = "This API updates the details of an existing assessment identified by the title."
    )
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

    @Operation(
            summary = "Delete Assessment by Title",
            description = "This API deletes an existing assessment identified by the title."
    )
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

    @Operation(
            summary = "Get Assessments by Topic Name",
            description = "This API retrieves a list of assessments that belong to a specific topic."
    )
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
