package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.AssessmentResultDTO;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.AssessmentResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assessment-results")
@Tag(name = "Assessment Result Controller", description = "APIs for managing Assessment Results for students")
public class AssessmentResultController {

    @Autowired
    AssessmentResultService assessmentResultService;

    @Operation(
            summary = "Create a new Assessment Result",
            description = "This API creates a new assessment result for a student using the provided details."
    )
    @PostMapping
    public ResponseEntity<ApiResponseDto<AssessmentResultDTO>> create(@RequestBody AssessmentResultDTO dto) {
        try {
            AssessmentResultDTO saved = assessmentResultService.saveAssessmentResult(dto);
            return ResponseEntity.ok(
                    new ApiResponseDto<>(
                            EApiResponseMessage.DATA_SAVED.getMessage(),
                            EOperationStatus.RESULT_SUCCESS,
                            saved
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponseDto<>(
                            EApiResponseMessage.FAILURE.getMessage() + ": " + e.getMessage(),
                            EOperationStatus.RESULT_FAILURE,
                            null
                    )
            );
        }
    }

    @Operation(
            summary = "Update Assessment Result by Student Email",
            description = "This API updates an existing assessment result identified by the student's email."
    )
    @PutMapping("/{studentEmail}")
    public ResponseEntity<ApiResponseDto<AssessmentResultDTO>> update(
            @PathVariable String studentEmail,
            @RequestBody AssessmentResultDTO dto
    ) {
        try {
            AssessmentResultDTO updated = assessmentResultService.updateAssessmentResultByStudentEmail(studentEmail, dto);
            return ResponseEntity.ok(
                    new ApiResponseDto<>(
                            EApiResponseMessage.DATA_UPDATED.getMessage(),
                            EOperationStatus.RESULT_SUCCESS,
                            updated
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>(
                            EApiResponseMessage.DATA_NOT_FOUND.getMessage() + ": " + e.getMessage(),
                            EOperationStatus.RESULT_FAILURE,
                            null
                    )
            );
        }
    }

    @Operation(
            summary = "Delete Assessment Result by Student Email",
            description = "This API deletes an assessment result identified by the student's email."
    )
    @DeleteMapping("/{studentEmail}")
    public ResponseEntity<ApiResponseDto<String>> delete(
            @PathVariable String studentEmail,
            @RequestBody AssessmentResultDTO dto
    ) {
        try {
            String response = assessmentResultService.removeAssessmentResultByStudentEmail(studentEmail, dto);
            return ResponseEntity.ok(
                    new ApiResponseDto<>(
                            EApiResponseMessage.DATA_DELETED.getMessage(),
                            EOperationStatus.RESULT_SUCCESS,
                            response
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>(
                            EApiResponseMessage.DATA_NOT_FOUND.getMessage() + ": " + e.getMessage(),
                            EOperationStatus.RESULT_FAILURE,
                            null
                    )
            );
        }
    }

    @Operation(
            summary = "Get a specific Assessment Result by Student Email",
            description = "This API retrieves a specific assessment result for a student using their email and assessment details."
    )
    @GetMapping("/{studentEmail}")
    public ResponseEntity<ApiResponseDto<AssessmentResultDTO>> getOne(
            @PathVariable String studentEmail,
            @RequestBody AssessmentResultDTO dto
    ) {
        try {
            Optional<AssessmentResultDTO> result = assessmentResultService.findAssessmentResultByStudentEmail(studentEmail, dto);
            if (result.isPresent()) {
                return ResponseEntity.ok(
                        new ApiResponseDto<>(
                                EApiResponseMessage.DATA_FOUND.getMessage(),
                                EOperationStatus.RESULT_SUCCESS,
                                result.get()
                        )
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>(
                            EApiResponseMessage.DATA_NOT_FOUND.getMessage(),
                            EOperationStatus.RESULT_FAILURE,
                            null
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponseDto<>(
                            EApiResponseMessage.INTERNAL_ERROR.getMessage() + ": " + e.getMessage(),
                            EOperationStatus.RESULT_FAILURE,
                            null
                    )
            );
        }
    }

    @Operation(
            summary = "Get all Assessment Results of a Student",
            description = "This API retrieves all assessment results for a specific student identified by their email."
    )
    @GetMapping("/student/{studentEmail}")
    public ResponseEntity<ApiResponseDto<List<AssessmentResultDTO>>> getAllByStudent(
            @PathVariable String studentEmail
    ) {
        try {
            List<AssessmentResultDTO> results = assessmentResultService.findAllAssessmentResultsByStudentEmail(studentEmail);
            return ResponseEntity.ok(
                    new ApiResponseDto<>(
                            EApiResponseMessage.DATA_FOUND.getMessage(),
                            EOperationStatus.RESULT_SUCCESS,
                            results
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>(
                            EApiResponseMessage.DATA_NOT_FOUND.getMessage() + ": " + e.getMessage(),
                            EOperationStatus.RESULT_FAILURE,
                            null
                    )
            );
        }
    }

    @Operation(
            summary = "Get all Assessment Results",
            description = "This API retrieves all assessment results for all students in the system."
    )
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<AssessmentResultDTO>>> getAll() {
        try {
            List<AssessmentResultDTO> results = assessmentResultService.findAllAssessmentResults();
            return ResponseEntity.ok(
                    new ApiResponseDto<>(
                            EApiResponseMessage.DATA_FOUND.getMessage(),
                            EOperationStatus.RESULT_SUCCESS,
                            results
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponseDto<>(
                            EApiResponseMessage.INTERNAL_ERROR.getMessage() + ": " + e.getMessage(),
                            EOperationStatus.RESULT_FAILURE,
                            null
                    )
            );
        }
    }
}
