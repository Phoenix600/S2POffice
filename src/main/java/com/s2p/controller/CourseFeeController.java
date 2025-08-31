package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.CourseDto;
import com.s2p.dto.CourseFeeDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.CourseFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/courseFee")
public class CourseFeeController
{
    @Autowired
    CourseFeeService courseFeeService;

    // Create Course Fee
    @PostMapping
    public ResponseEntity<ApiResponseDto<CourseFeeDto>> createCourseFee(@RequestBody CourseFeeDto courseFeeDto) {
        CourseFeeDto created = courseFeeService.createCourseFee(courseFeeDto);

        ApiResponseDto<CourseFeeDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_SAVED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                created
        );
        return ResponseEntity.ok(response);
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CourseFeeDto>> getCourseFeeById(@PathVariable("id") UUID id) {
        CourseFeeDto dto = courseFeeService.getCourseFeeById(id);

        ApiResponseDto<CourseFeeDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                dto
        );
        return ResponseEntity.ok(response);
    }

    // Get all
    @GetMapping
    public ResponseEntity<ApiResponseDto<Set<CourseFeeDto>>> getAllCourses() {
        Set<CourseFeeDto> all = courseFeeService.getAllCourses();

        ApiResponseDto<Set<CourseFeeDto>> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                all
        );
        return ResponseEntity.ok(response);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CourseFeeDto>> updateCourseFee(
            @PathVariable("id") UUID id,
            @RequestBody CourseFeeDto courseFeeDto
    ) {
        CourseFeeDto updated = courseFeeService.updateCourseFeeById(id, courseFeeDto);

        ApiResponseDto<CourseFeeDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_UPDATED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                updated
        );
        return ResponseEntity.ok(response);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CourseFeeDto>> deleteCourseFee(@PathVariable("id") UUID id) {
        CourseFeeDto deleted = courseFeeService.deleteCourseFeeById(id);

        ApiResponseDto<CourseFeeDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_DELETED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                deleted
        );
        return ResponseEntity.ok(response);
    }


}
