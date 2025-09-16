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

//    POST  http://localhost:8080/api/v1/adminUser/create
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<AdminUserDto>> create(@RequestBody AdminUserDto dto) {
        AdminUserDto created = adminUserService.createAdminUser(dto);

        return ResponseEntity.ok(
                new ApiResponseDto<>(EApiResponseMessage.DATA_SAVED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS, created)
        );
    }


    //GET  http://localhost:8080/api/v1/adminUser/{username}
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

//    GET  http://localhost:8080/api/v1/adminUser/all-adminUsers
    @GetMapping("all-adminUsers")
    public ResponseEntity<ApiResponseDto<Set<AdminUserDto>>> getAll() {
        Set<AdminUserDto> all = adminUserService.getAllAdminUsers();

        return ResponseEntity.ok(
                new ApiResponseDto<>(EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS, all)
        );
    }

//    PUT  http://localhost:8080/api/v1/adminUser/update/{username}
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

//    DELETE  http://localhost:8080/api/v1/adminUser/delete/{username}
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
