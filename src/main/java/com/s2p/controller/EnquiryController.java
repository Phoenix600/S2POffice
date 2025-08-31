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

import java.util.Optional;
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

    @PutMapping("/update/{email}")
    public ResponseEntity<ApiResponseDto<EnquiryDto>> updateEnquiry(@PathVariable("email") String email, @RequestBody EnquiryDto enquiryDto) {
        Optional<EnquiryDto> updatedOpt = enquiryService.updateEnquiryByStudentEmail(email, enquiryDto);
        if (updatedOpt.isPresent()) {
            return ResponseEntity.ok(new ApiResponseDto<>("Enquiry updated successfully", EOperationStatus.RESULT_SUCCESS, updatedOpt.get()));
        } else {
            return ResponseEntity.status(404).body(new ApiResponseDto<>("Enquiry not found", EOperationStatus.RESULT_FAILURE, null));
        }
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<ApiResponseDto<Void>> deleteEnquiry(@PathVariable("email") String email) {
        boolean deleted = enquiryService.deleteEnquiryByStudentEmail(email);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponseDto<>("Enquiry deleted successfully", EOperationStatus.RESULT_SUCCESS, null));
        } else {
            return ResponseEntity.status(404).body(new ApiResponseDto<>("Enquiry not found", EOperationStatus.RESULT_FAILURE, null));
        }
    }
}
