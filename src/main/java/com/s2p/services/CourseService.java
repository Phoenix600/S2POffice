package com.s2p.services;

import com.s2p.dto.CourseDto;
import com.s2p.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService implements ICourseService{

    @Autowired
    CourseRepository courseRepository;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        return null;
    }

    @Override
    public CourseDto getCourseById(UUID courseId) {
        return null;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return List.of();
    }

    @Override
    public CourseDto partialUpdateCourseById(UUID courseId) {
        return null;
    }

    @Override
    public CourseDto updateCourseById(UUID courseId) {
        return null;
    }
}
