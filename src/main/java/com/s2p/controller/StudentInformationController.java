package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.StudentInformationDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.StudentInformationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @GetMapping("/{email}")
    public ResponseEntity<ApiResponseDto<StudentInformationDto>> getStudentByEmail(
            @PathVariable @NotBlank @Email String email) {

        Optional<StudentInformationDto> studentOpt = studentInformationService.getStudentByEmail(email);

        if (studentOpt.isEmpty()) {
            throw new RuntimeException("Student not found with email: " + email);
        }

        ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(studentOpt.get());

        return ResponseEntity.ok(response);
    }

    // --- DELETE STUDENT BY EMAIL ---
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<ApiResponseDto<Void>> deleteStudent(
            @PathVariable @NotBlank @Email String email) {

        studentInformationService.deleteStudentByEmail(email);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData(null);

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
