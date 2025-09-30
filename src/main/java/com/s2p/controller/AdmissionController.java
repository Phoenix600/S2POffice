package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.AdmissionDto;
import com.s2p.dto.ApiResponseDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.AdmissionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/admission")
@Tag(name = "Admission Controller", description = "APIs for managing student admissions")
public class AdmissionController {

    @Autowired
    private AdmissionServiceImpl admissionServiceImpl;

    @Operation(
            summary = "Create a new Admission",
            description = "This API creates a new student admission with the details provided in the request body."
    )
    //http://localhost:8080/api/v1/admission/create-admission
    @PostMapping("/create-admission")
    public ResponseEntity<ApiResponseDto<AdmissionDto>> createAdmission(@RequestBody @Valid AdmissionDto admissionDto) {
        AdmissionDto response = admissionServiceImpl.createAdmission(admissionDto);
        ApiResponseDto<AdmissionDto> apiResponseDto = new ApiResponseDto<>(
                EApiResponseMessage.DATA_SAVED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                response
        );
        return new ResponseEntity<>(apiResponseDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Admission by Date",
            description = "This API retrieves a student admission using the admission date provided in the path."
    )
    //http://localhost:8080/api/v1/admission/{admissionDate}
    @GetMapping("/{admissionDate}")
    public ResponseEntity<ApiResponseDto<AdmissionDto>> getByDate(@PathVariable String admissionDate) {
        AdmissionDto admission = admissionServiceImpl.getAdmissionByDate(LocalDate.parse(admissionDate));
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        admission
                )
        );
    }

    @Operation(
            summary = "Get All Admissions",
            description = "This API retrieves a list of all student admissions in the system."
    )
//    http://localhost:8080/api/v1/admission/getAllAdmissions
    @GetMapping("/getAllAdmissions")
    public ResponseEntity<ApiResponseDto<List<AdmissionDto>>> getAllAdmissions() {
        List<AdmissionDto> admissions = admissionServiceImpl.getAllAdmissions();
        ApiResponseDto<List<AdmissionDto>> response = new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                admissions
        );
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update Admission by Date",
            description = "This API updates the details of an existing student admission identified by the admission date."
    )
//    http://localhost:8080/api/v1/admission/update/{admissionDate}
    @PutMapping("/update/{admissionDate}")
    public ResponseEntity<ApiResponseDto<AdmissionDto>> updateByDate(
            @PathVariable String admissionDate,
            @RequestBody AdmissionDto dto) {
        AdmissionDto updated = admissionServiceImpl.updateAdmissionByDate(LocalDate.parse(admissionDate), dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    @Operation(
            summary = "Delete Admission by Date",
            description = "This API deletes an existing student admission identified by the admission date."
    )
//    http://localhost:8080/api/v1/admission/delete/{admissionDate}
    @DeleteMapping("/delete/{admissionDate}")
    public ResponseEntity<ApiResponseDto<Void>> deleteByDate(@PathVariable String admissionDate) {
        admissionServiceImpl.deleteAdmissionByDate(LocalDate.parse(admissionDate));
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_DELETED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        null
                )
        );
    }
}
