package com.s2p.util;

import com.s2p.dto.CourseFeeDto;
import com.s2p.model.CourseFees;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class CourseFeesUtility
{
    public final static CourseFees toCourseFeeEntity(CourseFeeDto courseFeeDto)
    {
        CourseFees courseFees = new CourseFees();

        courseFees.setCourseFeesID(courseFeeDto.getCourseFeesID());
        courseFees.setTransactionId(courseFeeDto.getTransactionId());
        courseFees.setCourseFees(courseFeeDto.getCourseFees());

        return courseFees;
    }

    public final static CourseFeeDto toCourseFeeDto(CourseFees courseFees)
    {
        CourseFeeDto courseFeeDto = new CourseFeeDto();

        courseFeeDto.setCourseFeesID(courseFees.getCourseFeesID());
        courseFeeDto.setTransactionId(courseFees.getTransactionId());
        courseFeeDto.setCourseFees(courseFees.getCourseFees());

        return courseFeeDto;
    }
}
