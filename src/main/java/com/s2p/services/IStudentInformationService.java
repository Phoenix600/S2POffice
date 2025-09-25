package com.s2p.services;

import com.s2p.dto.StudentInformationDto;

import java.util.List;

public interface IStudentInformationService {

    StudentInformationDto createStudent(StudentInformationDto dto);

    StudentInformationDto updateStudentInformationByEmail(String email, StudentInformationDto dto);

    String deleteStudent(String email);

    StudentInformationDto getStudentByEmail(String email);

    List<StudentInformationDto> getAllStudents();
}
