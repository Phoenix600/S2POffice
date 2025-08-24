package com.s2p.util;

import com.s2p.dto.CourseDto;
import com.s2p.dto.CourseFeeDto;
import com.s2p.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CourseUtility
{
    public abstract Course toCourseEntity(CourseDto courseDto);
    public abstract CourseDto toCourseDto(Course course);

}

