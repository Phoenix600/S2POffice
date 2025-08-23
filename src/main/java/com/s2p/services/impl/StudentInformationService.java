package com.s2p.services.impl;

import com.s2p.dto.StudentInformationDto;
import com.s2p.services.IStudentInformationService;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class StudentInformationService implements IStudentInformationService {
    @Override
    public StudentInformationDto createStudentInformation(StudentInformationDto studentInformationDto) {
        return null;
    }

    @Override
    public StudentInformationDto getStudentInformationById(UUID studentId) {
        return null;
    }

    @Override
    public Set<StudentInformationDto> getAllStudents() {
        return Set.of();
    }

    @Override
    public Set<StudentInformationDto> getAllStudentsByAdmissionDate(LocalDate admissionDate) {
        return Set.of();
    }

    @Override
    public StudentInformationDto partialUpdateStudentInformationById(UUID studentId) {
        return null;
    }

    @Override
    public StudentInformationDto updateStudentInformationById(UUID studentId) {
        return null;
    }

    @Override
    public StudentInformationDto deleteStudentInformationById(UUID studentId) {
        return null;
    }
}
