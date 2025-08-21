package com.s2p.services;

import com.s2p.dto.CourseFeeDto;
import com.s2p.repository.CourseFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CourseFeeService implements ICourseFeeService
{
    @Autowired
    CourseFeeRepository courseFeeRepository;

    @Override
    public CourseFeeDto createCourseFee(CourseFeeDto courseFeeDto) {
        return null;
    }

    @Override
    public CourseFeeDto getCourseFeeById(UUID courseFeeId) {
        return null;
    }

    @Override
    public Set<CourseFeeDto> getAllCourses() {
        return Set.of();
    }

    @Override
    public CourseFeeDto partialUpdateCourseFeeById(UUID courseFeeId) {
        return null;
    }

    @Override
    public CourseFeeDto updateCourseFeeById(UUID courseFeeId) {
        return null;
    }

    @Override
    public CourseFeeDto deleteCourseFeeById(UUID courseFeeId) {
        return null;
    }
}
