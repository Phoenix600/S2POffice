package com.s2p.util;

import com.s2p.dto.CourseDto;
import com.s2p.dto.CourseFeeDto;
import com.s2p.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class CourseUtility
{
    public final static Course toCourseEntity(CourseDto courseDto)
    {
        Course course = new Course();

        course.setCourseId(courseDto.getCourseId());
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course.setCourseDurationInMonths(courseDto.getCourseDurationInMonths());

        return course;
    }

    public final static CourseDto toCourseDto(Course course)
    {
        CourseDto courseDto = new CourseDto();

        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setDescription(course.getDescription());
        courseDto.setCourseDurationInMonths(course.getCourseDurationInMonths());

        return courseDto;
    }
}

