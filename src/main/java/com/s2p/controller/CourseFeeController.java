package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.CourseFeeDto;
import com.s2p.master.model.AcademicYear;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.CourseFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/courseFee")
@Tag(name = "Course Fee Management APIs", description = "CRUD operations and search functionality for Course Fees")
public class CourseFeeController {

    @Autowired
    CourseFeeService courseFeeService;

    @Operation(summary = "Create Course Fee", description = "Create a new course fee with details provided in the request body")
    @PostMapping("create-courseFees")
    public ResponseEntity<ApiResponseDto<CourseFeeDto>> createCourseFee(@RequestBody CourseFeeDto courseFeeDto) {
        CourseFeeDto created = courseFeeService.createCourseFee(courseFeeDto);

        ApiResponseDto<CourseFeeDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_SAVED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                created
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Fees by Course Name", description = "Retrieve all course fees associated with a specific course")
    @GetMapping("/course/{courseName}")
    public ApiResponseDto<List<CourseFeeDto>> getFeesByCourseName(@PathVariable String courseName) {
        List<CourseFeeDto> fees = courseFeeService.getFeesByCourseName(courseName);
        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                fees
        );
    }

    @Operation(summary = "Get Fees by Academic Year", description = "Retrieve course fees for a given academic year")
    @GetMapping("/year/{academicYearName}")
    public ApiResponseDto<List<CourseFeeDto>> getFeesByAcademicYear(@PathVariable AcademicYear academicYear) {
        List<CourseFeeDto> fees = courseFeeService.getFeesByAcademicYear(academicYear);
        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                fees
        );
    }

    @Operation(summary = "Get All Course Fees", description = "Fetch all course fees available in the system")
    @GetMapping("/getAllCourseFees")
    public ResponseEntity<ApiResponseDto<Set<CourseFeeDto>>> getAllCourseFees() {
        Set<CourseFeeDto> all = courseFeeService.getAllCourses();

        ApiResponseDto<Set<CourseFeeDto>> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                all
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update Course Fee", description = "Update course fees for a specific course by name")
    @PutMapping("/update/course/{courseName}")
    public ApiResponseDto<CourseFeeDto> updateCourseFeeByCourseName(@PathVariable String courseName,
                                                                    @RequestBody CourseFeeDto dto) {
        CourseFeeDto updated = courseFeeService.updateCourseFeeByCourseName(courseName, dto);
        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_UPDATED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                updated
        );
    }

    @Operation(summary = "Delete Course Fee", description = "Delete course fees associated with a specific course name")
    @DeleteMapping("/delete/course/{courseName}")
    public ApiResponseDto<String> deleteCourseFeesByCourseName(@PathVariable String courseName) {
        String message = courseFeeService.deleteCourseFeesByCourseName(courseName);
        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_DELETED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                message
        );
    }
}
