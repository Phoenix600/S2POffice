package com.s2p.services;

import com.s2p.dto.StudentInformationDto;

import java.util.Set;
import java.util.UUID;

public interface IStudentInformationService
{
    public abstract StudentInformationDto createStudentInformation(StudentInformationDto studentInformationDto);

    public abstract  StudentInformationDto getStudentInformationById(UUID studentId);

    public abstract Set<StudentInformationDto> getAllStudents();

    public abstract StudentInformationDto partialUpdateStudentInformationById(UUID studentId);

    public abstract StudentInformationDto updateStudentInformationById(UUID studentId);

    public abstract StudentInformationDto deleteStudentInformationById(UUID studentId);
}
