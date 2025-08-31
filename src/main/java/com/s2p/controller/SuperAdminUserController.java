package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.SuperAdminUserDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.SuperAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

public class SuperAdminUserController
{
    @Autowired
    SuperAdminUserService superAdminUserService;

    // Create
    @PostMapping
    public ResponseEntity<ApiResponseDto<SuperAdminUserDto>> createSuperAdminUser(
            @RequestBody SuperAdminUserDto dto) {
        SuperAdminUserDto created = superAdminUserService.createSuperAdminUser(dto);

        ApiResponseDto<SuperAdminUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_SAVED.getMessage(),
                EOperationStatus.RESULT_SUCCESS, created
        );
        return ResponseEntity.ok(response);
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<SuperAdminUserDto>> getSuperAdminUserById(@PathVariable("id") UUID id) {
        SuperAdminUserDto dto = superAdminUserService.getSuperAdminUserById(id);

        ApiResponseDto<SuperAdminUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                dto
        );
        return ResponseEntity.ok(response);
    }

    // Get all
    @GetMapping
    public ResponseEntity<ApiResponseDto<Set<SuperAdminUserDto>>> getAllSuperAdminUsers() {
        Set<SuperAdminUserDto> all = superAdminUserService.getAllSuperAdminUsers();

        ApiResponseDto<Set<SuperAdminUserDto>> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                all
        );
        return ResponseEntity.ok(response);
    }

    // Update
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponseDto<SuperAdminUserDto>> updateSuperAdminUser(
//            @PathVariable("id") UUID id,
//            @RequestBody SuperAdminUserDto dto) {
//        SuperAdminUserDto updated = superAdminUserService.updateSuperAdminUserById(id, dto);
//
//        ApiResponseDto<SuperAdminUserDto> response = new ApiResponseDto<>(
//                EApiResponseMessage.DATA_UPDATED.getMessage(),
//                EOperationStatus.RESULT_SUCCESS,
//                updated
//        );
//        return ResponseEntity.ok(response);
//    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<SuperAdminUserDto>> deleteSuperAdminUser(@PathVariable("id") UUID id) {
        SuperAdminUserDto deleted = superAdminUserService.deleteSuperAdminById(id);

        ApiResponseDto<SuperAdminUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_DELETED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                deleted
        );
        return ResponseEntity.ok(response);
    }
}
