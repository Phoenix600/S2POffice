package com.s2p.services.impl;

import com.s2p.dto.CourseDto;
import com.s2p.exceptions.AlreadyExistsException;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Course;
import com.s2p.repository.CourseRepository;
import com.s2p.services.ICourseService;
import com.s2p.util.CourseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseUtility courseUtility;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        if (courseRepository.existsByCourseName(courseDto.getCourseName())) {
            throw new AlreadyExistsException("Course already exists with name: " + courseDto.getCourseName());
        }

        Course course = courseUtility.toCourseEntity(courseDto);
        Course savedCourse = courseRepository.save(course);
        return courseUtility.toCourseDto(savedCourse);
    }

    @Override
    public CourseDto getCourseByName(String courseName) {
        Optional<Course> courseOptional = courseRepository.findByCourseName(courseName);

        if (courseOptional.isEmpty()) {
            throw new ResourceNotFoundException("Course not found with name: " + courseName);
        }

        return courseUtility.toCourseDto(courseOptional.get());
    }


    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found in the system.");
        }

        List<CourseDto> dtos = new ArrayList<>();
        for (Course course : courses) {
            dtos.add(courseUtility.toCourseDto(course));
        }

        return dtos;
    }

    @Override
    public CourseDto updateCourseByName(String courseName, CourseDto dto) {
        Optional<Course> optional = courseRepository.findByCourseName(courseName);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Course not found with name: " + courseName);
        }

        Course course = optional.get();
        course.setCourseName(dto.getCourseName());
        course.setDescription(dto.getDescription());
        course.setCourseDurationInMonths(dto.getCourseDurationInMonths());

        Course updated = courseRepository.save(course);
        return courseUtility.toCourseDto(updated);
    }

    @Override
    public void deleteCourseByName(String courseName)
    {
        Optional<Course> courseOptional = courseRepository.findByCourseName(courseName);

        if (courseOptional.isEmpty()) {
            throw new ResourceNotFoundException("Course not found with name: " + courseName);
        }

        courseRepository.delete(courseOptional.get());
    }

    @Override
    public List<CourseDto> searchCourses(String courseName, String description, Byte duration) {
        return List.of();
    }
}
