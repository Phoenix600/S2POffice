package com.s2p.services.impl;

import com.s2p.dto.CourseFeeDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.master.model.AcademicYear;
import com.s2p.model.CourseFees;
import com.s2p.repository.CourseFeeRepository;
import com.s2p.services.ICourseFeeService;
import com.s2p.util.AcademicYearUtility;
import com.s2p.util.CourseFeesUtility;
import com.s2p.util.CourseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseFeeService implements ICourseFeeService
{
    @Autowired
    CourseFeeRepository courseFeeRepository;

    @Autowired
    CourseFeesUtility courseFeesUtility;

    @Autowired
    AcademicYearUtility academicYearUtility;

    @Autowired
    CourseUtility courseUtility;

    @Override
    public CourseFeeDto createCourseFee(CourseFeeDto courseFeeDto)
    {
        CourseFees courseFee = courseFeesUtility.toCourseFeeEntity(courseFeeDto);
        CourseFees saved = courseFeeRepository.save(courseFee);
        return courseFeesUtility.toCourseFeeDto(saved);
    }

    @Override
    public List<CourseFeeDto> getFeesByCourseName(String courseName)
    {
        List<CourseFees> fees = courseFeeRepository.findByCourse_CourseName(courseName);
        if (fees.isEmpty()) {
            throw new ResourceNotFoundException("No fees found for course: " + courseName);
        }
        List<CourseFeeDto> dtos = new ArrayList<>();
        for (CourseFees f : fees) {
            dtos.add(courseFeesUtility.toCourseFeeDto(f));
        }
        return dtos;
    }

    @Override
    public List<CourseFeeDto> getFeesByAcademicYear(AcademicYear academicYear)
    {
        List<CourseFees> fees = courseFeeRepository.findByAcademicYear(academicYear);
        if (fees.isEmpty()) {
            throw new ResourceNotFoundException("No fees found for academic year: " + academicYear);
        }
        List<CourseFeeDto> dtos = new ArrayList<>();
        for (CourseFees f : fees) {
            dtos.add(courseFeesUtility.toCourseFeeDto(f));
        }
        return dtos;
    }


    @Override
    public Set<CourseFeeDto> getAllCourses()
    {
        List<CourseFees> allFees = courseFeeRepository.findAll();
        Set<CourseFeeDto> result = new HashSet<>();

        for (CourseFees courseFees : allFees) {
            result.add(courseFeesUtility.toCourseFeeDto(courseFees));
        }
        return result;
    }

    @Override
    public CourseFeeDto updateCourseFeeByCourseName(String courseName, CourseFeeDto dto)
    {
        List<CourseFees> fees = courseFeeRepository.findByCourse_CourseName(courseName);
        if (fees.isEmpty()) {
            throw new ResourceNotFoundException("Cannot update. Course fees not found for course: " + courseName);
        }

        CourseFees fee = fees.get(0);
        fee.setCourseFees(dto.getCourseFees());
        fee.setAcademicYear(academicYearUtility.toAcademicYearEntity(dto.getAcademicYearDto()));
        fee.setCourse(courseUtility.toCourseEntity(dto.getCourseDto()));

        CourseFees updated = courseFeeRepository.save(fee);
        return courseFeesUtility.toCourseFeeDto(updated);
    }

    @Override
    public String deleteCourseFeesByCourseName(String courseName)
    {
        List<CourseFees> fees = courseFeeRepository.findByCourse_CourseName(courseName);
        if (fees.isEmpty()) {
            throw new ResourceNotFoundException("Cannot delete. No fees found for course: " + courseName);
        }
        courseFeeRepository.deleteAll(fees);
        return "Course fees deleted successfully for course: " + courseName;
    }
}
