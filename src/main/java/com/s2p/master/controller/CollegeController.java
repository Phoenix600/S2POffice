package com.s2p.master.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.master.dto.CollegeDto;
import com.s2p.master.model.College;
import com.s2p.master.service.ICollegeService;
import com.s2p.message.EApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/college")
@RequiredArgsConstructor
@Tag(name = "College Management",
        description = "APIs for managing colleges")
public class CollegeController {

    private final ICollegeService collegeService;

    @PostMapping
    @Operation(summary = "Create a new college",
            description = "Creates and saves a new college based on the provided data.")
    public ResponseEntity<ApiResponseDto<CollegeDto>> createCollege(@Valid @RequestBody CollegeDto collegeDto) {
        College college = new College();
        college.setCollegeName(collegeDto.getCollegeName());
        college.setDepartmentSet(collegeDto.getDepartmentSet());

        College created = collegeService.createCollege(college);

        ApiResponseDto<CollegeDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(toDto(created));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all colleges",
            description = "Retrieves a list of all colleges from the system.")
    public ResponseEntity<ApiResponseDto<List<CollegeDto>>> getAllColleges() {
        List<College> colleges = collegeService.getAllColleges();
        List<CollegeDto> dtos = new ArrayList<>();

        for (College c : colleges) {
            dtos.add(toDto(c));
        }

        ApiResponseDto<List<CollegeDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(dtos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get college by ID",
            description = "Fetches the details of a specific college using its unique ID.")
    public ResponseEntity<ApiResponseDto<CollegeDto>> getCollegeById(@PathVariable UUID id) {
        College college = collegeService.getCollegeById(id);

        ApiResponseDto<CollegeDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(toDto(college));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{collegeName}")
    @Operation(summary = "Get college by name",
            description = "Fetches the details of a specific college using its name.")
    public ResponseEntity<ApiResponseDto<CollegeDto>> getCollegeByName(@PathVariable String collegeName) {
        College college = collegeService.getCollegeByName(collegeName);

        ApiResponseDto<CollegeDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(toDto(college));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update college by ID",
            description = "Updates an existing college using its unique ID.")
    public ResponseEntity<ApiResponseDto<CollegeDto>> updateCollege(@PathVariable UUID id,
                                                                    @Valid @RequestBody CollegeDto collegeDto) {
        College details = new College();
        details.setCollegeName(collegeDto.getCollegeName());
        details.setDepartmentSet(collegeDto.getDepartmentSet());

        College updated = collegeService.updateCollege(id, details);

        ApiResponseDto<CollegeDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
        response.setData(toDto(updated));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete college by ID",
            description = "Deletes a college using its unique ID.")
    public ResponseEntity<ApiResponseDto<String>> deleteCollegeById(@PathVariable UUID id) {
        collegeService.deleteCollegeById(id);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("College deleted successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/name/{collegeName}")
    @Operation(summary = "Delete college by name",
            description = "Deletes a college using its name.")
    public ResponseEntity<ApiResponseDto<String>> deleteCollegeByName(@PathVariable String collegeName) {
        collegeService.deleteCollegeByName(collegeName);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("College deleted successfully");
        return ResponseEntity.ok(response);
    }

    private CollegeDto toDto(College college) {
        return new CollegeDto(college.getCollegeId(), college.getCollegeName(), college.getDepartmentSet());
    }
}
