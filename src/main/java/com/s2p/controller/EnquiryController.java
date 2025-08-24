package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.EnquiryDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.EnquiryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/enquiry")
public class EnquiryController
{
    @Autowired
    EnquiryService enquiryService;

    // Create Enquiry
    @PostMapping
    public ResponseEntity<ApiResponseDto<EnquiryDto>> createEnquiry(@Valid @RequestBody EnquiryDto enquiryDto) {
        EnquiryDto saved = enquiryService.createEnquiry(enquiryDto);

        ApiResponseDto<EnquiryDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(saved);

        return ResponseEntity.ok(response);
    }

    // Get Enquiry by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<EnquiryDto>> getEnquiryById(@PathVariable("id") UUID id) {
        EnquiryDto enquiry = enquiryService.getEnquiryById(id);

        ApiResponseDto<EnquiryDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(enquiry);

        return ResponseEntity.ok(response);
    }

    // Get all Enquiries
    @GetMapping
    public ResponseEntity<ApiResponseDto<Set<EnquiryDto>>> getAllEnquiries() {
        Set<EnquiryDto> enquiries = enquiryService.getAllEnquiries();

        ApiResponseDto<Set<EnquiryDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(enquiries);

        return ResponseEntity.ok(response);
    }

    // Update Enquiry (Full Update)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<EnquiryDto>> updateEnquiry(@PathVariable("id") UUID enquiryId) {
        EnquiryDto updated = enquiryService.updateEnquiryById(enquiryId);

        ApiResponseDto<EnquiryDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
        response.setData(updated);

        return ResponseEntity.ok(response);
    }


    // Delete Enquiry
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteEnquiry(@PathVariable("id") UUID id) {
        enquiryService.deleteEnquiryById(id);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("Enquiry deleted successfully");

        return ResponseEntity.ok(response);
    }
}
