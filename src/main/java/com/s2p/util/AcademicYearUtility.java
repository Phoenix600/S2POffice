package com.s2p.util;

import com.s2p.master.dto.AcademicYearDto;
import com.s2p.master.model.AcademicYear;


public class AcademicYearUtility
{
    public final static AcademicYear toAcademicYearEntity(AcademicYearDto academicYearDto)
    {
        AcademicYear academicYear = new AcademicYear();

        academicYear.setAcademicYearId(academicYearDto.getAcademicYearId());
        academicYear.setAcademicYear(academicYearDto.getAcademicYear());

        return  academicYear;
    }

    public final static AcademicYearDto toAcademicYearDto(AcademicYear academicYear)
    {
        AcademicYearDto academicYearDto = new AcademicYearDto();

        academicYearDto.setAcademicYearId(academicYear.getAcademicYearId());
        academicYearDto.setAcademicYear(academicYear.getAcademicYear());

        return academicYearDto;
    }
}
