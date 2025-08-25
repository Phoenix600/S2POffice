package com.s2p.util;

import com.s2p.dto.CourseFeeDto;
import com.s2p.model.CourseFees;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CourseFeesUtility
{
    public abstract CourseFees toCourseFeeEntity(CourseFeeDto courseFeeDto);
    public abstract CourseFeeDto toCourseFeeDto(CourseFees courseFees);

}
