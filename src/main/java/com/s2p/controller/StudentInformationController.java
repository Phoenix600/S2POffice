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
import java.util.Set;
import java.util.UUID;

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

    // Get Student by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<StudentInformationDto>> getStudentById(@PathVariable("id") UUID studentId) {
        StudentInformationDto student = studentInformationService.getStudentInformationById(studentId);

        ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(student);

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


    //  Update Student by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<StudentInformationDto>> updateStudent(
            @PathVariable("id") UUID id,
            @Valid @RequestBody StudentInformationDto studentInformationDto) {

        StudentInformationDto updatedStudent =
                studentInformationService.updateStudentInformationById(id, studentInformationDto);

        ApiResponseDto<StudentInformationDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
        response.setData(updatedStudent);

        return ResponseEntity.ok(response);
    }


    // Delete Student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteStudent(@PathVariable("id") UUID id) {
        studentInformationService.deleteStudentInformationById(id);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("Student deleted successfully");

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
