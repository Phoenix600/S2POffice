package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.TeacherUserDto;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.TeacherUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/teacherUser")
public class TeacherUserController
{
    @Autowired
    TeacherUserService teacherUserService;

    //  POST:- http://localhost:8080/api/v1/teacherUser/create-teacherUser
    @PostMapping("create-teacherUser")
    public ResponseEntity<ApiResponseDto<TeacherUserDto>> createTeacherUser(
            @Valid @RequestBody TeacherUserDto teacherUserDto) {

        TeacherUserDto created = teacherUserService.createTeacherUser(teacherUserDto);

        ApiResponseDto<TeacherUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_SAVED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                created
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //  GET:-  http://localhost:8080/api/v1/teacherUser/{username}
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponseDto<TeacherUserDto>> getTeacherUserByUsername(
            @PathVariable @NotBlank String username) {

        Optional<TeacherUserDto> userOpt = teacherUserService.getTeacherUserByUsername(username);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("TeacherUser not found with username: " + username);
        }

        ApiResponseDto<TeacherUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                userOpt.get()
        );

        return ResponseEntity.ok(response);
    }


    //  GET:- http://localhost:8080/api/v1/teacherUser/teacherUsers
    // Get All Teachers
    @GetMapping("teacherUsers")
    public ResponseEntity<ApiResponseDto<Set<TeacherUserDto>>> getAllTeachers() {
        Set<TeacherUserDto> teachers = teacherUserService.getAllTeachers();

        ApiResponseDto<Set<TeacherUserDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(teachers);

        return ResponseEntity.ok(response);
    }


    //  PUT:- http://localhost:8080/api/v1/teacherUser/update/{username}
    @PutMapping("/update/{username}")
    public ResponseEntity<ApiResponseDto<TeacherUserDto>> updateTeacherUserByUsername(
            @PathVariable @NotBlank String username,
            @Valid @RequestBody TeacherUserDto teacherUserDto) {

        TeacherUserDto updated = teacherUserService.updateTeacherUserByUsername(username, teacherUserDto);

        ApiResponseDto<TeacherUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_UPDATED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                updated
        );

        return ResponseEntity.ok(response);
    }


    //  DELETE:- http://localhost:8080/api/v1/teacherUser/delete/{username}
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ApiResponseDto<Void>> deleteTeacherUserByUsername(
            @PathVariable @NotBlank String username) {

        teacherUserService.deleteTeacherUserByUsername(username);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_DELETED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                null
        );

        return ResponseEntity.ok(response);
    }


}
