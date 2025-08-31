package com.s2p.services;

import com.s2p.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public interface ICourseService
{
    public abstract CourseDto createCourse(CourseDto courseDto);

    public abstract CourseDto getCourseByName(String courseName);

    public abstract List<CourseDto> getAllCourses();

    public abstract CourseDto updateCourseByName(String courseName, CourseDto dto);

    public abstract void deleteCourseByName(String courseName);

    public abstract List<CourseDto> searchCourses(String courseName, String description, Byte duration);
}
