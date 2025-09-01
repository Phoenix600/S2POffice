package com.s2p.services;

import com.s2p.dto.CourseFeeDto;
import com.s2p.master.model.AcademicYear;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ICourseFeeService
{
    public abstract CourseFeeDto createCourseFee(CourseFeeDto courseFeeDto);

    public abstract List<CourseFeeDto> getFeesByCourseName(String courseName);

    public abstract List<CourseFeeDto> getFeesByAcademicYear(AcademicYear academicYear);

    public abstract Set<CourseFeeDto> getAllCourses();

    public abstract CourseFeeDto updateCourseFeeByCourseName(String courseName, CourseFeeDto dto);

    public abstract String deleteCourseFeesByCourseName(String courseName);
}
