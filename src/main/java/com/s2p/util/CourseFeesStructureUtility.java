package com.s2p.util;

import com.s2p.dto.CourseFeeStructureDto;
import com.s2p.model.CourseFeeStructure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CourseFeesStructureUtility
{
    public abstract CourseFeeStructure toCourseFeeStructureEntity(CourseFeeStructureDto courseFeeStructureDto);
    public abstract CourseFeeStructureDto toCourseFeeStructureDto(CourseFeeStructure courseFeeStructure);

}
