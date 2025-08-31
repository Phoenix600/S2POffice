package com.s2p.dto;

import com.s2p.master.model.AcademicYear;
import com.s2p.model.Course;
import com.s2p.model.CourseFeeStructure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseFeeDto
{
    private UUID courseFeesID;

    private Double courseFees;

    private CourseFeeStructure feeStructure;

    private AcademicYear academicYear;

    private Course course;
}
