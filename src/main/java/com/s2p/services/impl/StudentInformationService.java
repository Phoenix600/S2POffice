package com.s2p.services.impl;

import com.s2p.dto.StudentInformationDto;
import com.s2p.model.Batch;
import com.s2p.model.Course;
import com.s2p.model.StudentInformation;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.services.IStudentInformationService;
import com.s2p.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentInformationService implements IStudentInformationService {

    private final StudentInformationRepository studentInformationRepository;
    private final StudentInformationUtility studentInformationUtility;
    private final EnquiryUtility enquiryUtility;
    private final BatchUtility batchUtility;
    private final CourseUtility courseUtility;
    private final CourseFeesStructureUtility courseFeesStructureUtility;

    @Override
    public StudentInformationDto createStudent(StudentInformationDto dto) {
        StudentInformation entity = studentInformationUtility.toStudentInformationEntity(dto);
        StudentInformation savedEntity = studentInformationRepository.save(entity);
        return studentInformationUtility.toStudentInformationDto(savedEntity);
    }

    @Override
    public StudentInformationDto updateStudent(String email, StudentInformationDto dto) {
        Optional<StudentInformation> optionalStudent = studentInformationRepository.findByEmail(email);

        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student not found with email: " + email);
        }

        StudentInformation existingStudent = optionalStudent.get();

        // set updated fields
        existingStudent.setFirstName(dto.getFirstName());
        existingStudent.setLastName(dto.getLastName());
        existingStudent.setCollegeName(dto.getCollegeName());
        existingStudent.setDepartName(dto.getDepartName());
        existingStudent.setSemester(dto.getSemester());
        existingStudent.setPassingYear(dto.getPassingYear());
        existingStudent.setIsGraduated(dto.getIsGraduated());
        existingStudent.setIsAdmitted(dto.getIsAdmitted());
        existingStudent.setIsDiscontinued(dto.getIsDiscontinued());
        existingStudent.setReasonOfDiscontinue(dto.getReasonOfDiscontinue());
        existingStudent.setEnquiry(enquiryUtility.toEnquiryDto(existingStudent.getEnquiry()));
        existingStudent.setBatches((Set<Batch>) batchUtility.toBatchDto((Batch) existingStudent.getBatches()));
        existingStudent.setCourses((Set<Course>) courseUtility.toCourseDto((Course) existingStudent.getCourses()));
        existingStudent.setCourseFeeStructure(courseFeesStructureUtility.toCourseFeeStructureDto(existingStudent.getCourseFeeStructure()));

        StudentInformation updated = studentInformationRepository.save(existingStudent);
        return studentInformationUtility.toStudentInformationDto(updated);
    }

    @Override
    public String deleteStudent(String email) {
        Optional<StudentInformation> optionalStudent = studentInformationRepository.findByEmail(email);

        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student not found with email: " + email);
        }

        StudentInformation existingStudent = optionalStudent.get();
        studentInformationRepository.delete(existingStudent);

        return "Student with email " + email + " deleted successfully!";
    }

    @Override
    public StudentInformationDto getStudentByEmail(String email) {
        Optional<StudentInformation> optionalStudent = studentInformationRepository.findByEmail(email);

        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student not found with email: " + email);
        }

        return studentInformationUtility.toStudentInformationDto(optionalStudent.get());
    }

    @Override
    public List<StudentInformationDto> getAllStudents() {
        List<StudentInformation> students = studentInformationRepository.findAll();
        List<StudentInformationDto> studentDtos = new ArrayList<StudentInformationDto>();

        for (StudentInformation student : students) {
            StudentInformationDto dto = studentInformationUtility.toStudentInformationDto(student);
            studentDtos.add(dto);
        }

        return studentDtos;
    }
}

