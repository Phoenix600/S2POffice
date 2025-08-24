package com.s2p.services.impl;

import com.s2p.dto.CourseFeeDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.CourseFees;
import com.s2p.repository.CourseFeeRepository;
import com.s2p.services.ICourseFeeService;
import com.s2p.util.CourseFeesUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseFeeService implements ICourseFeeService
{
    @Autowired
    CourseFeeRepository courseFeeRepository;

    @Override
    public CourseFeeDto createCourseFee(CourseFeeDto courseFeeDto) {
        CourseFees courseFee = CourseFeesUtility.toCourseFeeEntity(courseFeeDto);
        CourseFees saved = courseFeeRepository.save(courseFee);
        return CourseFeesUtility.toCourseFeeDto(saved);
    }

    @Override
    public CourseFeeDto getCourseFeeById(UUID courseFeeId)
    {
        Optional<CourseFees> optionalCourseFee = courseFeeRepository.findById(courseFeeId);

        if (optionalCourseFee.isEmpty()) {
            throw new ResourceNotFoundException("CourseFee", "id", courseFeeId.toString());
        }

        return CourseFeesUtility.toCourseFeeDto(optionalCourseFee.get());
    }

    @Override
    public Set<CourseFeeDto> getAllCourses() {
        List<CourseFees> allFees = courseFeeRepository.findAll();
        Set<CourseFeeDto> result = new HashSet<>();

        for (CourseFees courseFees : allFees) {
            result.add(CourseFeesUtility.toCourseFeeDto(courseFees));
        }
        return result;
    }

    @Override
    public CourseFeeDto partialUpdateCourseFeeById(UUID courseFeeId) {
        return null;
    }

    @Override
    public CourseFeeDto updateCourseFeeById(UUID courseFeeId, CourseFeeDto courseFeeDto) {
        Optional<CourseFees> optionalCourseFee = courseFeeRepository.findById(courseFeeId);

        if (optionalCourseFee.isEmpty()) {
            throw new ResourceNotFoundException("CourseFee", "id", courseFeeId.toString());
        }

        CourseFees existing = optionalCourseFee.get();
        existing.setCourse(courseFeeDto.getCourse());
        existing.setCourseFees(courseFeeDto.getCourseFees());
        existing.setFeeStructure(courseFeeDto.getFeeStructure());
        existing.setAcademicYear(courseFeeDto.getAcademicYear());

        CourseFees updated = courseFeeRepository.save(existing);
        return CourseFeesUtility.toCourseFeeDto(updated);
    }

    @Override
    public CourseFeeDto deleteCourseFeeById(UUID courseFeeId) {
        Optional<CourseFees> optionalCourseFee = courseFeeRepository.findById(courseFeeId);

        if (optionalCourseFee.isEmpty()) {
            throw new ResourceNotFoundException("CourseFee", "id", courseFeeId.toString());
        }

        CourseFees courseFee = optionalCourseFee.get();
        courseFeeRepository.delete(courseFee);
        return CourseFeesUtility.toCourseFeeDto(courseFee);
    }
}
