package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.CourseFeeStructureDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.CourseFeeStructureService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Set;

@RestController
@RequestMapping("api/v1/courseFeeStructure")
@Tag(name = "Course Fee Structure APIs", description = "APIs for managing course fee structures by course or student")
public class CourseFeeStructureController {

    @Autowired
    CourseFeeStructureService courseFeeStructureService;

    @Operation(summary = "Create Course Fee Structure", description = "Create a new course fee structure")
    @PostMapping
    public ResponseEntity<ApiResponseDto<CourseFeeStructureDto>> createCourseFeeStructure(
            @RequestBody CourseFeeStructureDto dto) {
        CourseFeeStructureDto created = courseFeeStructureService.createCourseFeeStructure(dto);

        ApiResponseDto<CourseFeeStructureDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_SAVED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                created
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Fee Structure by Course Name", description = "Retrieve the fee structure associated with a specific course")
    @GetMapping("/course/{courseName}")
    public ResponseEntity<ApiResponseDto<CourseFeeStructureDto>> getFeeByCourseName(
            @PathVariable String courseName) {

        CourseFeeStructureDto dto = courseFeeStructureService.getFeeStructureByCourseName(courseName);

        ApiResponseDto<CourseFeeStructureDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                dto
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get All Course Fee Structures", description = "Fetch all course fee structures available")
    @GetMapping
    public ResponseEntity<ApiResponseDto<Set<CourseFeeStructureDto>>> getAllCourseFeeStructures() {
        Set<CourseFeeStructureDto> all = courseFeeStructureService.getAllCourseFeeStructures();

        ApiResponseDto<Set<CourseFeeStructureDto>> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                all
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Fee Structure by Student Email", description = "Retrieve the fee structure for a specific student using their email address")
    @GetMapping("/student")
    public ResponseEntity<ApiResponseDto<CourseFeeStructureDto>> getFeeByStudentEmail(
            @RequestParam @Email(message = "Invalid email format") String email) {

        CourseFeeStructureDto dto = courseFeeStructureService.getFeeStructureByStudentEmail(email);

        ApiResponseDto<CourseFeeStructureDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                dto
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update Fee Structure by Student Email", description = "Update the fee structure for a student using their email address")
    @PutMapping("/student")
    public ResponseEntity<ApiResponseDto<CourseFeeStructureDto>> updateFeeByStudentEmail(
            @RequestParam @Email(message = "Invalid email format") String email,
            @Valid @RequestBody CourseFeeStructureDto dto) {

        CourseFeeStructureDto updated = courseFeeStructureService.updateFeeStructureByStudentEmail(email, dto);

        ApiResponseDto<CourseFeeStructureDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_UPDATED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                updated
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete Fee Structure by Student Email", description = "Delete the fee structure associated with a student email")
    @DeleteMapping("/student")
    public ResponseEntity<ApiResponseDto<String>> deleteFeeByStudentEmail(
            @RequestParam @Email(message = "Invalid email format") String email) {

        courseFeeStructureService.deleteFeeStructureByStudentEmail(email);

        ApiResponseDto<String> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_DELETED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                null
        );

        return ResponseEntity.ok(response);
    }
}
