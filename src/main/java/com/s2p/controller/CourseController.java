package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.CourseDto;
import com.s2p.exceptions.AlreadyExistsException;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@Tag(name = "Course Management APIs", description = "CRUD operations and search functionality for Courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Operation(summary = "Create Course", description = "Create a new course with details provided in the request body")
    @PostMapping("/create-course")
    public ResponseEntity<ApiResponseDto<CourseDto>> createCourse(@Valid @RequestBody CourseDto courseDto) {
        try {
            CourseDto created = courseService.createCourse(courseDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponseDto<>(EApiResponseMessage.DATA_SAVED.getMessage(), EOperationStatus.RESULT_SUCCESS, created)
            );
        } catch (AlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponseDto<>(ex.getMessage(), EOperationStatus.RESULT_FAILURE, null)
            );
        }
    }

    @Operation(summary = "Get Course by Name", description = "Retrieve details of a course using its name")
    @GetMapping("/{courseName}")
    public ResponseEntity<ApiResponseDto<CourseDto>> getCourseByName(@PathVariable String courseName) {
        try {
            CourseDto found = courseService.getCourseByName(courseName);
            return ResponseEntity.ok(
                    new ApiResponseDto<>(EApiResponseMessage.DATA_FOUND.getMessage(), EOperationStatus.RESULT_SUCCESS, found)
            );
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>(ex.getMessage(), EOperationStatus.RESULT_FAILURE, null)
            );
        }
    }

    @Operation(summary = "Update Course", description = "Update an existing course by its name with new details")
    @PutMapping("/update/{courseName}")
    public ResponseEntity<ApiResponseDto<CourseDto>> updateCourseByName(
            @PathVariable String courseName,
            @Valid @RequestBody CourseDto courseDto) {
        try {
            CourseDto updated = courseService.updateCourseByName(courseName, courseDto);
            return ResponseEntity.ok(
                    new ApiResponseDto<>(EApiResponseMessage.DATA_UPDATED.getMessage(), EOperationStatus.RESULT_SUCCESS, updated)
            );
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>(ex.getMessage(), EOperationStatus.RESULT_FAILURE, null)
            );
        }
    }

    @Operation(summary = "Delete Course", description = "Delete a course by its name")
    @DeleteMapping("/delete/{courseName}")
    public ResponseEntity<ApiResponseDto<String>> deleteCourseByName(@PathVariable String courseName) {
        try {
            courseService.deleteCourseByName(courseName);
            return ResponseEntity.ok(
                    new ApiResponseDto<>(EApiResponseMessage.DATA_DELETED.getMessage(), EOperationStatus.RESULT_SUCCESS, "Course deleted successfully")
            );
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>(ex.getMessage(), EOperationStatus.RESULT_FAILURE, null)
            );
        }
    }

    @Operation(summary = "Search Courses", description = "Search courses using optional filters: name, description, or duration")
    @GetMapping("/search")
    public List<CourseDto> searchCourses(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Byte duration
    ) {
        return courseService.searchCourses(courseName, description, duration);
    }
}
