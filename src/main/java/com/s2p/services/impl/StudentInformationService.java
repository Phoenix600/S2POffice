package com.s2p.services.impl;

import com.s2p.dto.StudentInformationDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.StudentInformation;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.services.IStudentInformationService;
import com.s2p.util.StudentInformationUtility;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;

import java.time.LocalDate;
import java.util.*;


@RequiredArgsConstructor
public class StudentInformationService implements IStudentInformationService
{
    public final StudentInformationRepository studentInformationRepository;


    @Override
    public StudentInformationDto createStudentInformation(StudentInformationDto studentInformationDto) throws BadRequestException {
            List<StudentInformation> allStudents = studentInformationRepository.findAll();

            for (StudentInformation existingStudent : allStudents) {
                if (existingStudent.getEmail().equalsIgnoreCase(studentInformationDto.getEmail())) {
                    throw new BadRequestException(
                            "Student already exists with email: " + studentInformationDto.getEmail()
                    );
                }
            }

            StudentInformation student = StudentInformationUtility.toStudentInformationEntity(studentInformationDto);
            StudentInformation savedStudent = studentInformationRepository.save(student);

            return StudentInformationUtility.toStudentInformationDto(savedStudent);
    }

    @Override
    public StudentInformationDto getStudentInformationById(UUID studentId)
    {
        Optional<StudentInformation> optionalStudent = studentInformationRepository.findById(studentId);

        if (optionalStudent.isEmpty()) {
            throw new ResourceNotFoundException("Student", "id", studentId.toString());
        }

        return StudentInformationUtility.toStudentInformationDto(optionalStudent.get());
    }

    @Override
    public Set<StudentInformationDto> getAllStudents()
    {
        List<StudentInformation> allStudents = studentInformationRepository.findAll();
        Set<StudentInformationDto> result = new HashSet<>();

        for (StudentInformation student : allStudents) {
            result.add(StudentInformationUtility.toStudentInformationDto(student));
        }

        return result;
    }

    @Override
    public Set<StudentInformationDto> getAllStudentsByAdmissionDate(LocalDate admissionDate)
    {
        List<StudentInformation> students = studentInformationRepository.findByAdmissionDate(admissionDate);
        Set<StudentInformationDto> result = new HashSet<>();

        for (StudentInformation student : students) {
            result.add(StudentInformationUtility.toStudentInformationDto(student));
        }

        return result;
    }

    @Override
    public StudentInformationDto partialUpdateStudentInformationById(UUID studentId) {
        return null;
    }


    @Override
    public StudentInformationDto updateStudentInformationById(UUID studentId, StudentInformationDto studentInformationDto) {
        Optional<StudentInformation> optionalStudent = studentInformationRepository.findById(studentId);

        if (optionalStudent.isEmpty()) {
            throw new ResourceNotFoundException("Student", "id", studentId.toString());
        }

        StudentInformation existingStudent = optionalStudent.get();
        existingStudent.setFirstName(studentInformationDto.getFirstName());
        existingStudent.setLastName(studentInformationDto.getLastName());
        existingStudent.setEmail(studentInformationDto.getEmail());
        existingStudent.setCollegeName(studentInformationDto.getCollegeName());
        existingStudent.setDegreeName(studentInformationDto.getDegreeName());
        existingStudent.setPassingYear(studentInformationDto.getPassingYear());
        existingStudent.setIsGraduated(studentInformationDto.getIsGraduated());


        StudentInformation updatedStudent = studentInformationRepository.save(existingStudent);
        return StudentInformationUtility.toStudentInformationDto(updatedStudent);
        }

    @Override
    public StudentInformationDto deleteStudentInformationById(UUID studentId) {
         Optional<StudentInformation> optionalStudent = studentInformationRepository.findById(studentId);

        if (optionalStudent.isEmpty()) {
            throw new ResourceNotFoundException("Student", "id", studentId.toString());
        }

        StudentInformation student = optionalStudent.get();
        studentInformationRepository.delete(student);

        return StudentInformationUtility.toStudentInformationDto(student);
    }
}
