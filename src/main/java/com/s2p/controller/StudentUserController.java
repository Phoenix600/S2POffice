package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.StudentUserDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.StudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/studentUsers")
public class StudentUserController
{

    @Autowired
    StudentUserService studentUserService;

    //  Create StudentUser
    @PostMapping
    public ResponseEntity<ApiResponseDto<StudentUserDto>> createStudentUser(@RequestBody StudentUserDto studentUserDto) {
        StudentUserDto created = studentUserService.createStudentUser(studentUserDto);

        ApiResponseDto<StudentUserDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(created);

        return ResponseEntity.ok(response);
    }

    //  Get StudentUser by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<StudentUserDto>> getStudentUserById(@PathVariable("id") UUID studentUserId) {
        StudentUserDto dto = studentUserService.getStudentUserById(studentUserId);

        ApiResponseDto<StudentUserDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());

        return ResponseEntity.ok(response);
    }

    // Get All StudentUsers
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<StudentUserDto>>> getAllStudentUsers() {
        List<StudentUserDto> users = studentUserService.getAllStudentUsers();

        ApiResponseDto<List<StudentUserDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(users);

        return ResponseEntity.ok(response);
    }

    // Update StudentUser by ID
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponseDto<StudentUserDto>> updateStudentUser(
//            @PathVariable("id") UUID id,
//            @Valid @RequestBody StudentUserDto studentUserDto) {
//
//        StudentUserDto updated = studentUserService.updateStudentUserById(id, studentUserDto);
//
//        ApiResponseDto<StudentUserDto> response = new ApiResponseDto<>();
//        response.setStatus(EOperationStatus.RESULT_SUCCESS);
//        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
//        response.setData(updated);
//
//        return ResponseEntity.ok(response);
//    }

    // Delete StudentUser by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<StudentUserDto>> deleteStudentUserById(@PathVariable("id") UUID id) {
        StudentUserDto deleted = studentUserService.deleteStudentUserById(id);

        ApiResponseDto<StudentUserDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData(deleted);

        return ResponseEntity.ok(response);
    }
}
