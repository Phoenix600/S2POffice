package com.s2p.services;

import com.s2p.dto.CourseFeeDto;

import java.util.Set;
import java.util.UUID;

public interface ICourseFeeService
{
    public abstract CourseFeeDto createCourseFee(CourseFeeDto courseFeeDto);

    public abstract CourseFeeDto getCourseFeeById(UUID courseFeeId);

    public abstract Set<CourseFeeDto> getAllCourses();

    public abstract CourseFeeDto partialUpdateCourseFeeById(UUID courseFeeId);

    public abstract CourseFeeDto updateCourseFeeById(UUID courseFeeId);

    public abstract CourseFeeDto deleteCourseFeeById(UUID courseFeeId);
}
