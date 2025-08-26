package com.s2p.services.impl;

import com.s2p.dto.CourseDto;
import com.s2p.model.Course;
import com.s2p.repository.CourseRepository;
import com.s2p.repository.specifications.CourseSpecification;
import com.s2p.services.ICourseService;
import com.s2p.util.CourseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseUtility courseUtility;

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
    public List<CourseDto> searchCourses(String courseName, String description, Byte duration) {
        Specification<Course> spec = Specification.anyOf(CourseSpecification.hasCourseName(courseName))
                .or(CourseSpecification.hasDescription(description))
                .or(CourseSpecification.hasDuration(duration));

        List<Course> courses = courseRepository.findAll(spec);
        List<CourseDto> courseDtos = new ArrayList<>();

        for (Course course : courses) {
            CourseDto dto = courseUtility.toCourseDto(course);
            courseDtos.add(dto);
        }

        return courseDtos;
    }
}
