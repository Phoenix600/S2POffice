package com.s2p.util;

import com.s2p.dto.CourseFeeStructureDto;
import com.s2p.model.CourseFeeStructure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class CourseFeesStructureUtility
{
    public final static CourseFeeStructure toCourseFeeStructureEntity(CourseFeeStructureDto courseFeeStructureDto)
    {
        CourseFeeStructure courseFeeStructure = new CourseFeeStructure();

        courseFeeStructure.setAmount(courseFeeStructureDto.getAmount());

        return courseFeeStructure;
    }

    public final static CourseFeeStructureDto toCourseFeeStructureDto(CourseFeeStructure courseFeeStructure)
    {
        CourseFeeStructureDto courseFeeStructureDto = new CourseFeeStructureDto();

        courseFeeStructureDto.setAmount(courseFeeStructure.getAmount());

        return courseFeeStructureDto;
    }
}
