package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.StudentUserDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.StudentUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/studentUsers")
@Tag(name = "Student User Management", description = "APIs for managing student users including create, read, update, and delete operations")
public class StudentUserController {

    @Autowired
    StudentUserService studentUserService;

    @PostMapping("create-studentUser")
    @Operation(
            summary = "Create Student User",
            description = "Creates a new student user record with the provided details."
    )
    public ResponseEntity<ApiResponseDto<StudentUserDto>> createStudentUser(@RequestBody StudentUserDto studentUserDto) {
        StudentUserDto created = studentUserService.createStudentUser(studentUserDto);

        ApiResponseDto<StudentUserDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(created);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{username}")
    @Operation(
            summary = "Get Student User by Username",
            description = "Fetches the student user details by their username."
    )
    public ResponseEntity<ApiResponseDto<StudentUserDto>> getByUsername(@PathVariable String username) {
        Optional<StudentUserDto> userOpt = studentUserService.getStudentUserByUsername(username);

        if (userOpt.isPresent()) {
            ApiResponseDto<StudentUserDto> response = new ApiResponseDto<>(
                    EApiResponseMessage.DATA_FOUND.getMessage(),
                    EOperationStatus.RESULT_SUCCESS,
                    userOpt.get()
            );
            return ResponseEntity.ok(response);
        }

        ApiResponseDto<StudentUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_NOT_FOUND.getMessage(),
                EOperationStatus.RESULT_FAILURE,
                null
        );
        return ResponseEntity.status(404).body(response);
    }

    @GetMapping("/studentUsers")
    @Operation(
            summary = "Get All Student Users",
            description = "Retrieves a list of all registered student users."
    )
    public ResponseEntity<ApiResponseDto<List<StudentUserDto>>> getAllStudentUsers() {
        List<StudentUserDto> users = studentUserService.getAllStudentUsers();

        ApiResponseDto<List<StudentUserDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(users);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{username}")
    @Operation(
            summary = "Update Student User",
            description = "Updates the details of an existing student user identified by their username."
    )
    public ResponseEntity<ApiResponseDto<StudentUserDto>> updateByUsername(
            @PathVariable String username,
            @Valid @RequestBody StudentUserDto studentUserDto) {

        StudentUserDto updated = studentUserService.updateStudentUserByUsername(username, studentUserDto);

        ApiResponseDto<StudentUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_UPDATED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                updated
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{username}")
    @Operation(
            summary = "Delete Student User",
            description = "Deletes a student user record identified by their username."
    )
    public ResponseEntity<ApiResponseDto<Void>> deleteByUsername(@PathVariable String username) {
        studentUserService.deleteStudentUserByUsername(username);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_DELETED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                null
        );

        return ResponseEntity.ok(response);
    }
}
