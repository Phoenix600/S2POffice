package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.StudentInformationDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.IStudentInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentInformationController {

    @Autowired
    private IStudentInformationService studentInformationService;

    // ✅ Create Student
    @PostMapping
    public ResponseEntity<ApiResponseDto<StudentInformationDto>> createStudent(@RequestBody StudentInformationDto dto) {
        StudentInformationDto created = studentInformationService.createStudent(dto);
        ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_SAVED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                created
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ✅ Get Student by email
    @GetMapping("/{email}")
    public ResponseEntity<ApiResponseDto<StudentInformationDto>> getStudentByEmail(@PathVariable String email) {
        try {
            StudentInformationDto student = studentInformationService.getStudentByEmail(email);
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

    // ✅ Get all Students
    @GetMapping
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

    // ✅ Update Student by email
    @PutMapping("/{email}")
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

    // ✅ Delete Student by email
    @DeleteMapping("/{email}")
    public ResponseEntity<ApiResponseDto<Void>> deleteStudent(@PathVariable String email) {
        try {
            studentInformationService.deleteStudent(email);
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
