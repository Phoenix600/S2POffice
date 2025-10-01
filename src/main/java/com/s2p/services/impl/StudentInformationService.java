package com.s2p.services.impl;

import com.s2p.dto.BatchDto;
import com.s2p.dto.CourseDto;
import com.s2p.dto.StudentInformationDto;
import com.s2p.model.Batch;
import com.s2p.model.Course;
import com.s2p.model.StudentInformation;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.services.IStudentInformationService;
import com.s2p.util.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentInformationService implements IStudentInformationService {

    private final StudentInformationRepository studentInformationRepository;
    private final StudentInformationUtility studentInformationUtility;
    private final EnquiryUtility enquiryUtility;
    private final BatchUtility batchUtility;
    private final CourseUtility courseUtility;
    private final CourseFeesStructureUtility courseFeeStructureUtility;

    @Override
    public StudentInformationDto createStudent(StudentInformationDto dto) {
        StudentInformation entity = studentInformationUtility.toStudentInformationEntity(dto);
        StudentInformation savedEntity = studentInformationRepository.save(entity);
        return studentInformationUtility.toStudentInformationDto(savedEntity);
    }

    @Override
    @Transactional
    public StudentInformationDto updateStudentInformationByEmail(String email, StudentInformationDto dto) {
        // Fetch existing StudentInformation by email
        Optional<StudentInformation> optionalStudent = studentInformationRepository.findByEmail(email);

        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student not found with email: " + email);
        }

        StudentInformation existingStudent = optionalStudent.get();

        // Update basic fields
        existingStudent.setFirstName(dto.getFirstName());
        existingStudent.setLastName(dto.getLastName());
        existingStudent.setEmail(dto.getEmail());
        existingStudent.setCollegeName(dto.getCollegeName());
        existingStudent.setDepartName(dto.getDepartName());
        existingStudent.setSemester(dto.getSemester());
        existingStudent.setPassingYear(dto.getPassingYear());
        existingStudent.setIsGraduated(dto.getIsGraduated());
        existingStudent.setIsAdmitted(dto.getIsAdmitted());
        existingStudent.setIsDiscontinued(dto.getIsDiscontinued());
        existingStudent.setReasonOfDiscontinue(dto.getReasonOfDiscontinue());

        if (dto.getEnquiryDto() != null) {
            existingStudent.setEnquiry(enquiryUtility.toEnquiryEntity(dto.getEnquiryDto()));
        }

        if (dto.getBatches() != null && !dto.getBatches().isEmpty()) {
            Set<Batch> batchEntities = new HashSet<>();
            for (BatchDto batchDto : dto.getBatches()) {
                batchEntities.add(batchUtility.toBatchEntity(batchDto));
            }
            existingStudent.setBatches(batchEntities);
        }

        if (dto.getCourses() != null && !dto.getCourses().isEmpty()) {
            Set<Course> courseEntities = new HashSet<>();
            for (CourseDto courseDto : dto.getCourses()) {
                courseEntities.add(courseUtility.toCourseEntity(courseDto));
            }
            existingStudent.setCourses(courseEntities);
        }

        if (dto.getCourseFeeStructureDto() != null) {
            existingStudent.setCourseFeeStructure(
                    courseFeeStructureUtility.toCourseFeeStructureEntity(dto.getCourseFeeStructureDto())
            );
        }

        StudentInformation updatedStudent = studentInformationRepository.save(existingStudent);

        return studentInformationUtility.toStudentInformationDto(updatedStudent);
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

