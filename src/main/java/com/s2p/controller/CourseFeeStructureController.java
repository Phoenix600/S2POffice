package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.CourseFeeStructureDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.CourseFeeStructureService;
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

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CourseFeeStructureDto>> getCourseFeeStructureById(@PathVariable("id") UUID courseFeeStructureId) {
        CourseFeeStructureDto dto = courseFeeStructureService.getCourseFeeStructureById(courseFeeStructureId);

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

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CourseFeeStructureDto>> updateCourseFeeStructure(
            @PathVariable("id") UUID courseFeeStructureId,
            @RequestBody CourseFeeStructureDto dto) {
        CourseFeeStructureDto updated = courseFeeStructureService.updateCourseFeeStructureById(courseFeeStructureId, dto);

        ApiResponseDto<CourseFeeStructureDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_UPDATED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                updated
        );
        return ResponseEntity.ok(response);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CourseFeeStructureDto>> deleteCourseFeeStructure(@PathVariable("id") UUID courseFeeStructureId) {
        CourseFeeStructureDto deleted = courseFeeStructureService.deleteCourseFeeStructureById(courseFeeStructureId);

        ApiResponseDto<CourseFeeStructureDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_DELETED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                deleted
        );
        return ResponseEntity.ok(response);
    }
}
