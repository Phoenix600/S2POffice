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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/admission")
public class AdmissionController
{
    @Autowired
    AdmissionServiceImpl admissionServiceImpl;

    //Done
    //http://localhost:8080/api/v1/admission/create-admission
    @PostMapping("/create-admission")
    public ResponseEntity<ApiResponseDto<AdmissionDto>> createAdmission(@RequestBody AdmissionDto admissionDto)
    {
        AdmissionDto response = admissionServiceImpl.createAdmission(admissionDto);

        ApiResponseDto apiResponseDto = new ApiResponseDto();

        return new ResponseEntity<>(apiResponseDto, HttpStatus.CREATED);
    }

    // Get Admission by ID
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

    // Get All Admissions
    @GetMapping("/getAllAdmissions")
    public ResponseEntity<ApiResponseDto<List<AdmissionDto>>> getAllAdmissions() {
        List<AdmissionDto> admissions = admissionServiceImpl.getAllAdmissions();

        ApiResponseDto<List<AdmissionDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(admissions);

        return ResponseEntity.ok(response);
    }

    //  Full Update Admission
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


    //  Delete Admission
    @DeleteMapping("/delete/{admissionDate}")
    public ResponseEntity<ApiResponseDto<Void>> deleteByDate(@PathVariable String admissionDate)
    {
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
