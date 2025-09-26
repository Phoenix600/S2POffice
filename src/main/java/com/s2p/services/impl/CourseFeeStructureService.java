package com.s2p.services.impl;

import com.s2p.dto.CourseFeeStructureDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Course;
import com.s2p.model.CourseFeeStructure;
import com.s2p.model.StudentUsers;
import com.s2p.repository.CourseFeeStructureRepository;
import com.s2p.repository.CourseRepository;
import com.s2p.repository.StudentUserRepository;
import com.s2p.services.ICourseFeeStructureService;
import com.s2p.util.CourseFeesStructureUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseFeeStructureService implements ICourseFeeStructureService
{
    @Autowired
    CourseFeeStructureRepository courseFeeStructureRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentUserRepository studentUsersRepository;

    @Autowired
    CourseFeesStructureUtility courseFeeStructureUtility;

    @Override
    @Transactional
    public CourseFeeStructureDto createCourseFeeStructure(CourseFeeStructureDto courseFeeStructureDto) {

        // Fetch course via courseName
        Optional<Course> course = courseRepository.findByCourseName(courseFeeStructureDto.getCourseDto().getCourseName());
        if (!course.isPresent()) {
            throw new RuntimeException("Course not found with name: " + courseFeeStructureDto.getCourseDto().getCourseName());
        }

        // Fetch student via username
        Optional<StudentUsers> studentUser = studentUsersRepository.findByUsername(courseFeeStructureDto.getStudentUserDto().getUsername());
        if (!studentUser.isPresent()) {
            throw new RuntimeException("Student user not found with username: " + courseFeeStructureDto.getStudentUserDto().getUsername());
        }

        // Map DTO to Entity
        CourseFeeStructure feeStructure = courseFeeStructureUtility.toCourseFeeStructureEntity(courseFeeStructureDto);
        feeStructure.setCourse(course.get());
        feeStructure.setStudentUsers(studentUser.get());

        // Save entity
        CourseFeeStructure savedFeeStructure = courseFeeStructureRepository.save(feeStructure);

        // Map back to DTO
        return courseFeeStructureUtility.toCourseFeeStructureDto(savedFeeStructure);
    }



    @Override
    public CourseFeeStructureDto getFeeStructureByCourseName(String courseName) {
        Optional<CourseFeeStructure> optional = courseFeeStructureRepository.findByCourse_CourseName(courseName);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Fee structure not found for course: " + courseName);
        }

        return courseFeeStructureUtility.toCourseFeeStructureDto(optional.get());
    }

    @Override
    public CourseFeeStructureDto getFeeStructureByStudentEmail(String email) {
        Optional<CourseFeeStructure> optional = courseFeeStructureRepository.findByCourse_CourseName(email);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Fee structure not found for course: " + email);
        }

        return courseFeeStructureUtility.toCourseFeeStructureDto(optional.get());
    }


    @Override
    public Set<CourseFeeStructureDto> getAllCourseFeeStructures() {
        List<CourseFeeStructure> structures = courseFeeStructureRepository.findAll();
        Set<CourseFeeStructureDto> result = new HashSet<>();

        for (CourseFeeStructure structure : structures) {
            result.add(courseFeeStructureUtility.toCourseFeeStructureDto(structure));
        }
        return result;
    }

    @Override
    public CourseFeeStructureDto updateFeeStructureByStudentEmail(String email, CourseFeeStructureDto dto) {
        Optional<CourseFeeStructure> courseFeeStructureOptional = courseFeeStructureRepository.findByStudentUsers_Email(email);

        if (courseFeeStructureOptional.isEmpty()) {
            throw new ResourceNotFoundException("Fee structure not found for student email: " + email);
        }

        CourseFeeStructure existingCourseFeeStructure = courseFeeStructureOptional.get();
        existingCourseFeeStructure.setDownPayment(dto.getDownPayment());
        existingCourseFeeStructure.setRemainingAmount(dto.getRemainingAmount());
        existingCourseFeeStructure.setIsDiscountGiven(dto.getIsDiscountGiven());
        existingCourseFeeStructure.setIsDiscountFactor(dto.getIsDiscountFactor());
        existingCourseFeeStructure.setNInstallments(dto.getNInstallments());
        existingCourseFeeStructure.setRemainingInstallments(dto.getRemainingInstallments());

        CourseFeeStructure updated = courseFeeStructureRepository.save(existingCourseFeeStructure);
        return courseFeeStructureUtility.toCourseFeeStructureDto(updated);
    }


    @Override
    public void deleteFeeStructureByStudentEmail(String email) {
        Optional<CourseFeeStructure> optional = courseFeeStructureRepository.findByStudentUsers_Email(email);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Fee structure not found for student email: " + email);
        }

        courseFeeStructureRepository.delete(optional.get());
    }
}
