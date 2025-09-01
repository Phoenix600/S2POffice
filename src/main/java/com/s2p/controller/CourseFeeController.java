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

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/courseFee")
public class CourseFeeController
{
    @Autowired
    CourseFeeService courseFeeService;

    // Create Course Fee
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

    @GetMapping("/course/{courseName}")
    public ApiResponseDto<List<CourseFeeDto>> getFeesByCourseName(@PathVariable String courseName) {
        List<CourseFeeDto> fees = courseFeeService.getFeesByCourseName(courseName);
        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                fees
        );
    }

    @GetMapping("/year/{academicYearName}")
    public ApiResponseDto<List<CourseFeeDto>> getFeesByAcademicYear(@PathVariable AcademicYear academicYear) {
        List<CourseFeeDto> fees = courseFeeService.getFeesByAcademicYear(academicYear);
        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                fees
        );
    }

    // Get all
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

    //Update
    @PutMapping("/update/course/{courseName}")
    public ApiResponseDto<CourseFeeDto> updateCourseFeeByCourseName(@PathVariable String courseName,
                                                                    @RequestBody CourseFeeDto dto)
    {
        CourseFeeDto updated = courseFeeService.updateCourseFeeByCourseName(courseName, dto);
        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_UPDATED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                updated
        );
    }

    // Delete
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
