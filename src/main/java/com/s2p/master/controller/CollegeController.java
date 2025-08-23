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

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/college")
@RequiredArgsConstructor
public class CollegeController {

    private final ICollegeService collegeService;

    // --- Create College ---
    @PostMapping
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

    // --- Get All Colleges ---
    @GetMapping
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

    // --- Get College By ID ---
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CollegeDto>> getCollegeById(@PathVariable UUID id) {
        College college = collegeService.getCollegeById(id);

        ApiResponseDto<CollegeDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(toDto(college));
        return ResponseEntity.ok(response);
    }

    // --- Get College By Name ---
    @GetMapping("/name/{collegeName}")
    public ResponseEntity<ApiResponseDto<CollegeDto>> getCollegeByName(@PathVariable String collegeName) {
        College college = collegeService.getCollegeByName(collegeName);

        ApiResponseDto<CollegeDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(toDto(college));
        return ResponseEntity.ok(response);
    }

    // --- Update College ---
    @PutMapping("/{id}")
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

    // --- Delete College By ID ---
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteCollegeById(@PathVariable UUID id) {
        collegeService.deleteCollegeById(id);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("College deleted successfully");
        return ResponseEntity.ok(response);
    }

    // --- Delete College By Name ---
    @DeleteMapping("/name/{collegeName}")
    public ResponseEntity<ApiResponseDto<String>> deleteCollegeByName(@PathVariable String collegeName) {
        collegeService.deleteCollegeByName(collegeName);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("College deleted successfully");
        return ResponseEntity.ok(response);
    }

    // -------- Helper Mapper --------
    private CollegeDto toDto(College college) {
        return new CollegeDto(college.getCollegeId(), college.getCollegeName(), college.getDepartmentSet());
    }
}
