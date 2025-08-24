package com.s2p.master.dto;

import com.s2p.model.CourseFees;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcademicYearDto
{
    private UUID academicYearId;

    private Integer academicYear;

    private List<CourseFees> courseFees = new ArrayList<>();
}
