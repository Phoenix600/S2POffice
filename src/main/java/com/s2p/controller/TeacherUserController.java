package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.TeacherUserDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.TeacherUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/teacherUser")
public class TeacherUserController
{
    @Autowired
    TeacherUserService teacherUserService;

    // Create TeacherUser
    @PostMapping
    public ResponseEntity<ApiResponseDto<TeacherUserDto>> createTeacher(@Valid @RequestBody TeacherUserDto teacherUserDto) {
        TeacherUserDto created = teacherUserService.createTeacherUser(teacherUserDto);

        ApiResponseDto<TeacherUserDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(created);

        return ResponseEntity.ok(response);
    }

    // Get TeacherUser by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<TeacherUserDto>> getTeacherById(@PathVariable("id") UUID id) {
        TeacherUserDto dto = teacherUserService.getTeacherById(id);

        ApiResponseDto<TeacherUserDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(dto);

        return ResponseEntity.ok(response);
    }

    // Get All Teachers
    @GetMapping
    public ResponseEntity<ApiResponseDto<Set<TeacherUserDto>>> getAllTeachers() {
        Set<TeacherUserDto> teachers = teacherUserService.getAllTeachers();

        ApiResponseDto<Set<TeacherUserDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(teachers);

        return ResponseEntity.ok(response);
    }

//    //  Update Teacher by ID
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponseDto<TeacherUserDto>> updateTeacher(
//            @PathVariable("id") UUID id,
//            @Valid @RequestBody TeacherUserDto teacherUserDto) {
//
//        TeacherUserDto updated = teacherUserService.updateTeacherUserById(id);
//
//        ApiResponseDto<TeacherUserDto> response = new ApiResponseDto<>();
//        response.setStatus(EOperationStatus.RESULT_SUCCESS);
//        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
//        response.setData(updated);
//
//        return ResponseEntity.ok(response);
//    }

    // Delete Teacher by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<TeacherUserDto>> deleteTeacher(@PathVariable("id") UUID id) {
        TeacherUserDto deleted = teacherUserService.deleteTeacherUserById(id);

        ApiResponseDto<TeacherUserDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData(deleted);

        return ResponseEntity.ok(response);
    }
}
