package com.s2p.services;

import com.s2p.dto.StudentInformationDto;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface IStudentInformationService {

    StudentInformationDto createStudent(StudentInformationDto dto);

    StudentInformationDto updateStudent(String email, StudentInformationDto dto);

    StudentInformationDto createStudentInformation(StudentInformationDto studentInformationDto) throws BadRequestException;

    String deleteStudent(String email);

    StudentInformationDto getStudentByEmail(String email);

    List<StudentInformationDto> getAllStudents();
}
