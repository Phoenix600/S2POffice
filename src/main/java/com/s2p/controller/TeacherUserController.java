package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.TeacherUserDto;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.TeacherUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/teacherUser")
@Tag(name = "Teacher User Management", description = "APIs for managing Teacher users, including CRUD operations, batch and course assignments")
public class TeacherUserController {

    @Autowired
    TeacherUserService teacherUserService;

    @Operation(summary = "Create Teacher User", description = "Creates a new Teacher user in the system")
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

    @Operation(summary = "Get Teacher User by Username", description = "Fetches details of a Teacher user using their username")
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

    @Operation(summary = "Get All Teacher Users", description = "Retrieves all Teacher users from the system")
    @GetMapping("teacherUsers")
    public ResponseEntity<ApiResponseDto<Set<TeacherUserDto>>> getAllTeachers() {
        Set<TeacherUserDto> teachers = teacherUserService.getAllTeachers();

        ApiResponseDto<Set<TeacherUserDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(teachers);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update Teacher User by Username", description = "Updates an existing Teacher user identified by their username")
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

    @Operation(summary = "Delete Teacher User by Username", description = "Deletes a Teacher user identified by their username")
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

    @Operation(summary = "Get Teachers by Batch", description = "Fetches all Teacher users assigned to a specific batch")
    @GetMapping("/batch/{batchId}")
    public Set<TeacherUserDto> getByBatch(@PathVariable UUID batchId) {
        return teacherUserService.getTeachersByBatch(batchId);
    }

    @Operation(summary = "Update Teacher by Batch", description = "Updates Teacher details and assigns them to a batch")
    @PutMapping("/{teacherId}/batch/{batchId}")
    public TeacherUserDto updateByBatch(@PathVariable UUID teacherId,
                                        @PathVariable UUID batchId,
                                        @RequestBody TeacherUserDto dto) {
        return teacherUserService.updateTeacherByBatch(batchId, teacherId, dto);
    }

    @Operation(summary = "Remove Teacher from Batch", description = "Removes a Teacher user from a batch")
    @DeleteMapping("/{teacherId}/batch/{batchId}")
    public void removeFromBatch(@PathVariable UUID teacherId, @PathVariable UUID batchId) {
        teacherUserService.removeTeacherFromBatch(batchId, teacherId);
    }

    @Operation(summary = "Get Teachers by Course", description = "Fetches all Teacher users assigned to a specific course")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Set<TeacherUserDto>> getTeachersByCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(teacherUserService.getTeachersByCourse(courseId));
    }

    @Operation(summary = "Update Teacher by Course", description = "Updates Teacher details and assigns them to a course")
    @PutMapping("/course/{courseId}/{teacherId}")
    public ResponseEntity<TeacherUserDto> updateTeacherByCourse(
            @PathVariable UUID courseId,
            @PathVariable UUID teacherId,
            @RequestBody TeacherUserDto dto) {
        return ResponseEntity.ok(teacherUserService.updateTeacherByCourse(courseId, teacherId, dto));
    }

    @Operation(summary = "Remove Teacher from Course", description = "Removes a Teacher user from a course")
    @DeleteMapping("/course/{courseId}/{teacherId}")
    public ResponseEntity<Void> removeTeacherFromCourse(
            @PathVariable UUID courseId,
            @PathVariable UUID teacherId) {
        teacherUserService.removeTeacherFromCourse(courseId, teacherId);
        return ResponseEntity.noContent().build();
    }

}
