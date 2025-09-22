package com.s2p.services;

import com.s2p.dto.StudentInformationDto;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.Optional;

public interface IStudentInformationService {

    StudentInformationDto createStudent(StudentInformationDto dto);

    StudentInformationDto updateStudent(String email, StudentInformationDto dto);

    StudentInformationDto createStudentInformation(StudentInformationDto studentInformationDto) throws BadRequestException;

    String deleteStudentByEmail(String email);

    Optional<StudentInformationDto> getStudentByEmail(String email);

    List<StudentInformationDto> getAllStudents();

    public StudentInformationDto updateStudentByEmail(String email, StudentInformationDto studentInformationDto);
    public List<StudentInformationDto> searchStudents(
            String firstName,
            String lastName,
            String email,
            String collegeName,
            String degreeName,
            String semester,
            String passingYear,
            Boolean isGraduated);
}
