package com.s2p.services;

import com.s2p.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public interface ICourseService
{
    public abstract CourseDto createCourse(CourseDto courseDto);

    public abstract CourseDto getCourseById(UUID courseId);

    public abstract List<CourseDto> getAllCourses();

    public abstract CourseDto partialUpdateCourseById(UUID courseId);

    public abstract CourseDto updateCourseById(UUID courseId);
}
