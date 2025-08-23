package com.s2p.services;

import com.s2p.dto.StudentInformationDto;
import org.apache.coyote.BadRequestException;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public interface IStudentInformationService
{
    public abstract StudentInformationDto createStudentInformation(StudentInformationDto studentInformationDto) throws BadRequestException;

    public abstract  StudentInformationDto getStudentInformationById(UUID studentId);

    public abstract Set<StudentInformationDto> getAllStudents();

    public abstract Set<StudentInformationDto> getAllStudentsByAdmissionDate(LocalDate admissionDate);

    public abstract StudentInformationDto partialUpdateStudentInformationById(UUID studentId);

    public abstract StudentInformationDto updateStudentInformationById(UUID studentId, StudentInformationDto studentInformationDto);

    public abstract StudentInformationDto deleteStudentInformationById(UUID studentId);
}
