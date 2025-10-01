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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/enquiry")
@Tag(name = "Enquiry APIs", description = "APIs for managing student enquiries")
public class EnquiryController {

    @Autowired
    EnquiryService enquiryService;

    @Operation(
            summary = "Create Enquiry",
            description = "Create a new enquiry record for a student"
    )
    //POST  http://localhost:8080/api/v1/enquiry/create-enquiry
    @PostMapping("/create-enquiry")
    public ResponseEntity<ApiResponseDto<EnquiryDto>> createEnquiry(@Valid @RequestBody EnquiryDto enquiryDto) {
        EnquiryDto saved = enquiryService.createEnquiry(enquiryDto);

        ApiResponseDto<EnquiryDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(saved);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get Enquiries by Date",
            description = "Fetch all enquiries made on a specific date"
    )
    //GET   http://localhost:8080/api/v1/enquiry/date/{date}
    @GetMapping("/date/{date}")
    public ResponseEntity<ApiResponseDto<List<EnquiryDto>>> getEnquiriesByDate(@PathVariable("date") String date) {
        LocalDate enquiryDate = LocalDate.parse(date);
        List<EnquiryDto> enquiries = enquiryService.getEnquiriesByDate(enquiryDate);
        return ResponseEntity.ok(new ApiResponseDto<>("Enquiries fetched successfully", EOperationStatus.RESULT_SUCCESS, enquiries));
    }

    @Operation(
            summary = "Get Enquiry by Student Email",
            description = "Retrieve a student's enquiry details using their email address"
    )
    //GET   http://localhost:8080/api/v1/enquiry/student/{email}
    @GetMapping("/student/{email}")
    public ResponseEntity<ApiResponseDto<EnquiryDto>> getEnquiryByStudentEmail(@PathVariable("email") String email) {
        Optional<EnquiryDto> enquiryOpt = enquiryService.getEnquiryByStudentEmail(email);
        if (enquiryOpt.isPresent()) {
            return ResponseEntity.ok(new ApiResponseDto<>("Enquiry fetched successfully", EOperationStatus.RESULT_SUCCESS, enquiryOpt.get()));
        } else {
            return ResponseEntity.status(404).body(new ApiResponseDto<>("Enquiry not found", EOperationStatus.RESULT_FAILURE, null));
        }
    }


    @Operation(
            summary = "Get All Enquiries",
            description = "Fetch all enquiries stored in the system"
    )
    //GET   http://localhost:8080/api/v1/enquiry/getAllEnquiries
    @GetMapping("/getAllEnquiries")
    public ResponseEntity<ApiResponseDto<Set<EnquiryDto>>> getAllEnquiries() {
        Set<EnquiryDto> enquiries = enquiryService.getAllEnquiries();

        ApiResponseDto<Set<EnquiryDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(enquiries);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update Enquiry by Email",
            description = "Update an existing enquiry record using the student's email"
    )
    @PutMapping("/update/{email}")
    public ResponseEntity<ApiResponseDto<EnquiryDto>> updateEnquiry(@PathVariable("email") String email, @RequestBody EnquiryDto enquiryDto) {
        Optional<EnquiryDto> updatedOpt = enquiryService.updateEnquiryByStudentEmail(email, enquiryDto);
        if (updatedOpt.isPresent()) {
            return ResponseEntity.ok(new ApiResponseDto<>("Enquiry updated successfully", EOperationStatus.RESULT_SUCCESS, updatedOpt.get()));
        } else {
            return ResponseEntity.status(404).body(new ApiResponseDto<>("Enquiry not found", EOperationStatus.RESULT_FAILURE, null));
        }
    }

    @Operation(
            summary = "Delete Enquiry by Email",
            description = "Delete a student's enquiry record using their email"
    )
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
