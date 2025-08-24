package com.s2p.controller;

import com.s2p.dto.AdmissionDto;
import com.s2p.dto.ApiResponseDto;
import com.s2p.services.impl.AdmissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
