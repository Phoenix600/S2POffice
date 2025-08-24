package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.AdmissionDto;
import com.s2p.dto.ApiResponseDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.AdmissionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/admission")
public class AdmissionController
{
    @Autowired
    AdmissionServiceImpl admissionServiceImpl;

    //http://localhost:8080/api/v1/admission/create-admission
    @PostMapping("/create-admission")
    public ResponseEntity<ApiResponseDto<AdmissionDto>> createAdmission(@RequestBody AdmissionDto admissionDto)
    {
        AdmissionDto response = admissionServiceImpl.createAdmission(admissionDto);

        ApiResponseDto apiResponseDto = new ApiResponseDto();

        return new ResponseEntity<>(apiResponseDto, HttpStatus.CREATED);
    }

    // Get Admission by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AdmissionDto>> getAdmissionById(@PathVariable("id") UUID id) {
        AdmissionDto admission = admissionServiceImpl.getAdmissionById(id);

        ApiResponseDto<AdmissionDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(admission);

        return ResponseEntity.ok(response);
    }

    // Get All Admissions
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<AdmissionDto>>> getAllAdmissions() {
        List<AdmissionDto> admissions = admissionServiceImpl.getAllAdmissions();

        ApiResponseDto<List<AdmissionDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(admissions);

        return ResponseEntity.ok(response);
    }

    //  Full Update Admission
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AdmissionDto>> updateAdmission(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AdmissionDto admissionDto) {

        AdmissionDto updatedAdmission = admissionServiceImpl.updateAdmissionById(id, admissionDto);

        ApiResponseDto<AdmissionDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
        response.setData(updatedAdmission);

        return ResponseEntity.ok(response);

    }

    //  Delete Admission
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteAdmission(@PathVariable("id") UUID id) {
        admissionServiceImpl.deleteAdmissionById(id);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("Admission deleted successfully");

        return ResponseEntity.ok(response);
    }
}
