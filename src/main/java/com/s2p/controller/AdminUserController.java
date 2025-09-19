package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.AdminUserDto;
import com.s2p.dto.ApiResponseDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/adminUser")
@Tag(name = "AdminUser Controller", description = "APIs for managing Admin Users")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @Operation(
            summary = "Create a new Admin User",
            description = "This API creates a new admin user with the details provided in the request body."
    )
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<AdminUserDto>> create(@RequestBody AdminUserDto dto) {
        AdminUserDto created = adminUserService.createAdminUser(dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(EApiResponseMessage.DATA_SAVED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS, created)
        );
    }

    @Operation(
            summary = "Get Admin User by username",
            description = "This API retrieves an admin user's details using their unique username."
    )
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponseDto<AdminUserDto>> getByUsername(@PathVariable String username) {
        AdminUserDto adminUser = adminUserService.getAdminUserByUsername(username);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        adminUser
                )
        );
    }

    @Operation(
            summary = "Get all Admin Users",
            description = "This API retrieves a set of all admin users in the system."
    )
    @GetMapping("/all-adminUsers")
    public ResponseEntity<ApiResponseDto<Set<AdminUserDto>>> getAll() {
        Set<AdminUserDto> all = adminUserService.getAllAdminUsers();
        return ResponseEntity.ok(
                new ApiResponseDto<>(EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS, all)
        );
    }

    @Operation(
            summary = "Update Admin User by username",
            description = "This API updates the details of an existing admin user identified by their username."
    )
    @PutMapping("/update/{username}")
    public ResponseEntity<ApiResponseDto<AdminUserDto>> updateByUsername(
            @PathVariable String username,
            @RequestBody AdminUserDto dto) {
        AdminUserDto updated = adminUserService.updateAdminUserByUsername(username, dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    @Operation(
            summary = "Delete Admin User by username",
            description = "This API deletes an existing admin user identified by their username."
    )
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ApiResponseDto<Void>> deleteByUsername(@PathVariable String username) {
        adminUserService.deleteAdminUserByUsername(username);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_DELETED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        null
                )
        );
    }
}
