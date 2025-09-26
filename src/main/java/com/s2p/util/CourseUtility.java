package com.s2p.util;

import com.s2p.dto.CourseDto;
import com.s2p.model.Course;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CourseUtility
{
    public abstract Course toCourseEntity(CourseDto courseDto);
    public abstract CourseDto toCourseDto(Course course);

}

