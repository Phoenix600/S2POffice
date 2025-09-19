package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.SuperAdminUserDto;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.SuperAdminUserService;
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

@RestController
@RequestMapping("/api/v1/superAdminUser")
@Tag(name = "Super Admin User Management", description = "APIs for managing super admin users including create, fetch, update, and delete operations")
public class SuperAdminUserController {

    @Autowired
    SuperAdminUserService superAdminUserService;

    // CREATE
    @PostMapping("/create")
    @Operation(
            summary = "Create Super Admin User",
            description = "Creates a new super admin user with the provided details."
    )
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

    // GET BY USERNAME
    @GetMapping("/{username}")
    @Operation(
            summary = "Get Super Admin User by Username",
            description = "Fetches a super admin user by their unique username."
    )
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

    // GET ALL
    @GetMapping("/all")
    @Operation(
            summary = "Get All Super Admin Users",
            description = "Retrieves a list of all super admin users in the system."
    )
    public ResponseEntity<ApiResponseDto<Set<SuperAdminUserDto>>> getAllSuperAdminUsers() {
        Set<SuperAdminUserDto> all = superAdminUserService.getAllSuperAdminUsers();

        ApiResponseDto<Set<SuperAdminUserDto>> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                all
        );
        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/update/{username}")
    @Operation(
            summary = "Update Super Admin User",
            description = "Updates the details of an existing super admin user identified by their username."
    )
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

    // DELETE
    @DeleteMapping("/delete/{username}")
    @Operation(
            summary = "Delete Super Admin User",
            description = "Deletes a super admin user identified by their username."
    )
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
