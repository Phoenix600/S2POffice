package com.s2p.master.controller;

import com.s2p.master.dto.AcademicYearDto;
import com.s2p.master.service.AcademicYearService;
import com.s2p.model.ApiResponseLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/AcademicYear")
public class AcademicYearController
{
    @Autowired
    AcademicYearService academicYearService;

    //POST:-  http://localhost:8080/api/v1/AcademicYear/createAcademicYear
    @PostMapping("/createAcademicYear")
    public ResponseEntity<ApiResponseLog<AcademicYearDto>> createAcademicYear(@RequestBody AcademicYearDto academicYearDto)
    {
        AcademicYearDto response = academicYearService.createAcademicYear(academicYearDto);

        ApiResponseLog<AcademicYearDto> apiResponseLog = new ApiResponseLog<>();

//        apiResponseLog.setStatus(response.getAcademicYear());

        return new ResponseEntity<>(HttpStatus.CREATED);


    }
}
