package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.AdminUserDto;
import com.s2p.dto.ApiResponseDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/adminUser")
public class AdminUserController
{
    @Autowired
    AdminUserService adminUserService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<AdminUserDto>> create(@RequestBody AdminUserDto dto) {
        AdminUserDto created = adminUserService.createAdminUser(dto);

        return ResponseEntity.ok(
                new ApiResponseDto<>(EApiResponseMessage.DATA_SAVED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS, created)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AdminUserDto>> getById(@PathVariable UUID id) {
        AdminUserDto dto = adminUserService.getAdminUserById(id);

        return ResponseEntity.ok(
                new ApiResponseDto<>(EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS, dto)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<Set<AdminUserDto>>> getAll() {
        Set<AdminUserDto> all = adminUserService.getAllAdminUsers();

        return ResponseEntity.ok(
                new ApiResponseDto<>(EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS, all)
        );
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponseDto<AdminUserDto>> update(
//            @PathVariable UUID id, @RequestBody AdminUserDto dto) {
//        AdminUserDto updated = adminUserService.updateAdminUserById(id, dto);
//
//        return ResponseEntity.ok(
//                new ApiResponseDto<>(EApiResponseMessage.DATA_UPDATED.getMessage(),
//                        EOperationStatus.RESULT_SUCCESS, updated)
//        );
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AdminUserDto>> delete(@PathVariable UUID id) {
        AdminUserDto deleted = adminUserService.deleteAdminUserById(id);

        return ResponseEntity.ok(
                new ApiResponseDto<>(EApiResponseMessage.DATA_DELETED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS, deleted)
        );
    }
}
