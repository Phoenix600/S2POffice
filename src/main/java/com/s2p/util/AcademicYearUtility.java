package com.s2p.util;

import com.s2p.master.dto.AcademicYearDto;
import com.s2p.master.model.AcademicYear;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AcademicYearUtility
{
    public abstract AcademicYear toAcademicYearEntity(AcademicYearDto academicYearDto);
    public abstract AcademicYearDto toAcademicYearDto(AcademicYear academicYear);
}
