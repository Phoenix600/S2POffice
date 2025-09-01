package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.StudentInformationDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.StudentInformationService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/studentInformation")
public class StudentInformationController
{
    @Autowired
    StudentInformationService studentInformationService;


    //POST  http://localhost:8080/api/v1/studentInformation/createStudent
    @PostMapping("/createStudent")
    public ResponseEntity<ApiResponseDto<StudentInformationDto>> createStudent(
            @Valid @RequestBody StudentInformationDto studentInformationDto) throws BadRequestException {

        StudentInformationDto createdStudent = studentInformationService.createStudentInformation(studentInformationDto);

        ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(createdStudent);

        return ResponseEntity.ok(response);
    }


    //  Get All Students
    @GetMapping
    public ResponseEntity<ApiResponseDto<Set<StudentInformationDto>>> getAllStudents() {
        Set<StudentInformationDto> students = studentInformationService.getAllStudents();

        ApiResponseDto<Set<StudentInformationDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(students);

        return ResponseEntity.ok(response);
    }

    // PUT  http://localhost:8080/api/v1/studentInformation/update/{email}
    @PutMapping("/update/{email}")
    public ResponseEntity<ApiResponseDto<StudentInformationDto>> updateStudent(
            @PathVariable("email") String email,
            @Valid @RequestBody StudentInformationDto studentInformationDto) {

        StudentInformationDto updatedStudent = studentInformationService.updateStudentByEmail(email, studentInformationDto);

        ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
        response.setData(updatedStudent);

        return ResponseEntity.ok(response);
    }

    // GET  http://localhost:8080/api/v1/studentInformation/get/{email}
    @GetMapping("/get/{email}")
    public ResponseEntity<Optional<StudentInformationDto>> getStudentByEmail(
            @PathVariable("email") String email) {

        Optional<StudentInformationDto> response = studentInformationService.getStudentByEmail(email);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/search")
    public ResponseEntity<List<StudentInformationDto>> searchStudents(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String collegeName,
            @RequestParam(required = false) String degreeName,
            @RequestParam(required = false) String semester,
            @RequestParam(required = false) String passingYear,
            @RequestParam(required = false) Boolean isGraduated
    ) {
        List<StudentInformationDto> results = studentInformationService.searchStudents(
                firstName, lastName, email, collegeName, degreeName, semester, passingYear, isGraduated
        );
        return ResponseEntity.ok(results);
    }

}
