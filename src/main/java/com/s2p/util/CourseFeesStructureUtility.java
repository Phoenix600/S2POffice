package com.s2p.util;

import com.s2p.dto.CourseFeeStructureDto;
import com.s2p.model.CourseFeeStructure;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CourseFeesStructureUtility
{
    public abstract CourseFeeStructure toCourseFeeStructureEntity(CourseFeeStructureDto courseFeeStructureDto);
    public abstract CourseFeeStructure toCourseFeeStructureDto(CourseFeeStructure courseFeeStructure);

}
