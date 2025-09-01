package com.s2p.services;

import com.s2p.dto.StudentInformationDto;
import org.apache.coyote.BadRequestException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IStudentInformationService
{
    public abstract StudentInformationDto createStudentInformation(StudentInformationDto studentInformationDto) throws BadRequestException;

    public abstract Optional<StudentInformationDto> getStudentByEmail(String email);

    public abstract Set<StudentInformationDto> getAllStudents();

    public abstract StudentInformationDto updateStudentByEmail(String email, StudentInformationDto studentInformationDto);

    public abstract void deleteStudentByEmail(String email);

    public List<StudentInformationDto> searchStudents(String firstName,
                                                      String lastName,
                                                      String email,
                                                      String collegeName,
                                                      String degreeName,
                                                      String semester,
                                                      String passingYear,
                                                      Boolean isGraduated);
}
