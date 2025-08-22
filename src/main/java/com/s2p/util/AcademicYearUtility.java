package com.s2p.util;

import com.s2p.master.AcademicYear;
import com.s2p.master.Dto.AcademicYearDto;

public class AcademicYearUtility
{
    public static AcademicYear toAcademicYearEntity(AcademicYearDto academicYearDto)
    {
        AcademicYear academicYear = new AcademicYear();

        academicYear.setAcademicYearId(academicYearDto.getAcademicYearId());
        academicYear.setAcademicYear(academicYearDto.getAcademicYear());

        return  academicYear;
    }

    public static AcademicYearDto toAcademicYearDto(AcademicYear academicYear)
    {
        AcademicYearDto academicYearDto = new AcademicYearDto();

        academicYearDto.setAcademicYearId(academicYear.getAcademicYearId());
        academicYearDto.setAcademicYear(academicYear.getAcademicYear());

        return academicYearDto;
    }
}
