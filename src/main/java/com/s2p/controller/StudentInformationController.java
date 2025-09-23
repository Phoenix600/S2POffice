package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.StudentInformationDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.IStudentInformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Information Management", description = "APIs for managing student information including create, read, update, and delete operations")
public class StudentInformationController {

    @Autowired
    private IStudentInformationService studentInformationService;

    @PostMapping
    @Operation(
            summary = "Create a new Student",
            description = "Creates a new student record with the provided student information."
    )
    public ResponseEntity<ApiResponseDto<StudentInformationDto>> createStudent(@RequestBody StudentInformationDto dto) {
        StudentInformationDto created = studentInformationService.createStudent(dto);
        ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_SAVED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                created
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{email}")
    @Operation(
            summary = "Get Student by Email",
            description = "Retrieves a student's information using their email address."
    )
    public ResponseEntity<ApiResponseDto<StudentInformationDto>> getStudentByEmail(@PathVariable String email) {
        try {
            StudentInformationDto student = studentInformationService.getStudentByEmail(email).get();
            ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>(
                    EApiResponseMessage.DATA_FOUND.getMessage(),
                    EOperationStatus.RESULT_SUCCESS,
                    student
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>(
                    EApiResponseMessage.RESOURCE_NOT_FOUND.getMessage(),
                    EOperationStatus.RESULT_FAILURE,
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    @Operation(
            summary = "Get all Students",
            description = "Fetches a list of all registered students."
    )
    public ResponseEntity<ApiResponseDto<List<StudentInformationDto>>> getAllStudents() {
        List<StudentInformationDto> students = studentInformationService.getAllStudents();
        EApiResponseMessage message = students.isEmpty() ? EApiResponseMessage.DATA_NOT_FOUND : EApiResponseMessage.DATA_FOUND;

        ApiResponseDto<List<StudentInformationDto>> response = new ApiResponseDto<>(
                message.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                students
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{email}")
    @Operation(
            summary = "Update Student by Email",
            description = "Updates the details of an existing student using their email address."
    )
    public ResponseEntity<ApiResponseDto<StudentInformationDto>> updateStudent(
            @PathVariable String email,
            @RequestBody StudentInformationDto dto) {
        try {
            StudentInformationDto updated = studentInformationService.updateStudent(email, dto);
            ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>(
                    EApiResponseMessage.DATA_UPDATED.getMessage(),
                    EOperationStatus.RESULT_SUCCESS,
                    updated
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>(
                    EApiResponseMessage.RESOURCE_NOT_FOUND.getMessage(),
                    EOperationStatus.RESULT_FAILURE,
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{email}")
    @Operation(
            summary = "Delete Student by Email",
            description = "Deletes a student's record using their email address."
    )
    public ResponseEntity<ApiResponseDto<Void>> deleteStudent(@PathVariable String email) {
        try {
            studentInformationService.deleteStudentByEmail(email);
            ApiResponseDto<Void> response = new ApiResponseDto<>(
                    EApiResponseMessage.DATA_DELETED.getMessage(),
                    EOperationStatus.RESULT_SUCCESS,
                    null
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponseDto<Void> response = new ApiResponseDto<>(
                    EApiResponseMessage.RESOURCE_NOT_FOUND.getMessage(),
                    EOperationStatus.RESULT_FAILURE,
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
