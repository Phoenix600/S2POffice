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

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/courseFeeStructure")
public class CourseFeeStructureController
{
    @Autowired
    CourseFeeStructureService courseFeeStructureService;

    // Create
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

    // Get fee structure by course name
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

    // Get all
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

    // Get fee structure by student email
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

    // Update fee structure by student email
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

    // Delete fee structure by student email
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
