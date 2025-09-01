package com.s2p.services.impl;

import com.s2p.dto.CourseFeeStructureDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.CourseFeeStructure;
import com.s2p.repository.CourseFeeStructureRepository;
import com.s2p.services.ICourseFeeStructureService;
import com.s2p.util.CourseFeesStructureUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseFeeStructureService implements ICourseFeeStructureService
{
    @Autowired
    CourseFeeStructureRepository courseFeeStructureRepository;

    @Autowired
    CourseFeesStructureUtility courseFeesStructureUtility;

    @Override
    public CourseFeeStructureDto createCourseFeeStructure(CourseFeeStructureDto courseFeeStructureDto) {
        CourseFeeStructure entity = courseFeesStructureUtility.toCourseFeeStructureEntity(courseFeeStructureDto);
        CourseFeeStructure saved = courseFeeStructureRepository.save(entity);
        return courseFeesStructureUtility.toCourseFeeStructureDto(saved);
    }

    @Override
    public CourseFeeStructureDto getFeeStructureByCourseName(String courseName) {
        Optional<CourseFeeStructure> optional = courseFeeStructureRepository.findByCourse_CourseName(courseName);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Fee structure not found for course: " + courseName);
        }

        return courseFeesStructureUtility.toCourseFeeStructureDto(optional.get());
    }

    @Override
    public CourseFeeStructureDto getFeeStructureByStudentEmail(String email) {
        Optional<CourseFeeStructure> optional = courseFeeStructureRepository.findByCourse_CourseName(email);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Fee structure not found for course: " + email);
        }

        return courseFeesStructureUtility.toCourseFeeStructureDto(optional.get());
    }


    @Override
    public Set<CourseFeeStructureDto> getAllCourseFeeStructures() {
        List<CourseFeeStructure> structures = courseFeeStructureRepository.findAll();
        Set<CourseFeeStructureDto> result = new HashSet<>();

        for (CourseFeeStructure structure : structures) {
            result.add(courseFeesStructureUtility.toCourseFeeStructureDto(structure));
        }
        return result;
    }

    @Override
    public CourseFeeStructureDto updateFeeStructureByStudentEmail(String email, CourseFeeStructureDto dto) {
        Optional<CourseFeeStructure> optional = courseFeeStructureRepository.findByStudentUsers_Email(email);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Fee structure not found for student email: " + email);
        }

        CourseFeeStructure existing = optional.get();
        existing.setDownPayment(dto.getDownPayment());
        existing.setRemainingAmount(dto.getRemainingAmount());
        existing.setIsDiscountGiven(dto.getIsDiscountGiven());
        existing.setIsDiscountFactor(dto.getIsDiscountFactor());
        existing.setNInstallments(dto.getNInstallments());
        existing.setRemainingInstallments(dto.getRemainingInstallments());

        CourseFeeStructure updated = courseFeeStructureRepository.save(existing);
        return courseFeesStructureUtility.toCourseFeeStructureDto(updated);
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
