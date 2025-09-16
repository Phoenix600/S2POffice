package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.SuperAdminUserDto;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.SuperAdminUserService;
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
@RequestMapping("/api/v1/superAdminUser")
public class SuperAdminUserController {
    @Autowired
    SuperAdminUserService superAdminUserService;

    // POST: http://localhost:8080/api/v1/superAdminUser/create
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<SuperAdminUserDto>> createSuperAdminUser(
            @Valid @RequestBody SuperAdminUserDto superAdminUserDto) {

        SuperAdminUserDto created = superAdminUserService.createSuperAdminUser(superAdminUserDto);

        ApiResponseDto<SuperAdminUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_SAVED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                created
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET: http://localhost:8080/api/v1/superAdminUser/{username}
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponseDto<SuperAdminUserDto>> getByUsername(
            @PathVariable @NotBlank String username) {

        Optional<SuperAdminUserDto> userOpt = superAdminUserService.getSuperAdminUserByUsername(username);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("SuperAdminUser not found with username: " + username);
        }

        ApiResponseDto<SuperAdminUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                userOpt.get()
        );

        return ResponseEntity.ok(response);
    }

    // Get all
    // GET: http://localhost:8080/api/v1/superAdminUser/all
    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<Set<SuperAdminUserDto>>> getAllSuperAdminUsers() {
        Set<SuperAdminUserDto> all = superAdminUserService.getAllSuperAdminUsers();

        ApiResponseDto<Set<SuperAdminUserDto>> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                all
        );
        return ResponseEntity.ok(response);
    }

    // PUT: http://localhost:8080/api/v1/superAdminUser/update/{username}
    @PutMapping("/update/{username}")
    public ResponseEntity<ApiResponseDto<SuperAdminUserDto>> updateByUsername(
            @PathVariable @NotBlank String username,
            @Valid @RequestBody SuperAdminUserDto superAdminUserDto) {

        SuperAdminUserDto updated = superAdminUserService.updateSuperAdminUserByUsername(username, superAdminUserDto);

        ApiResponseDto<SuperAdminUserDto> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_UPDATED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                updated
        );

        return ResponseEntity.ok(response);
    }

    // DELETE: http://localhost:8080/api/v1/superAdminUser/delete/{username}
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ApiResponseDto<Void>> deleteByUsername(@PathVariable @NotBlank String username) {

        superAdminUserService.deleteSuperAdminUserByUsername(username);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_DELETED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                null
        );

        return ResponseEntity.ok(response);
    }
}
