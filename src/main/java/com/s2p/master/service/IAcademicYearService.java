package com.s2p.master.service;

import com.s2p.master.Dto.AcademicYearDto;

import java.util.Set;
import java.util.UUID;

public interface IAcademicYearService
{
    public abstract AcademicYearDto createAcademicYear(AcademicYearDto academicYearDto);

    public abstract AcademicYearDto getAcademicYearById(UUID academicYearId);

    public abstract Set<AcademicYearDto> getAllAcademicYear();

    public abstract AcademicYearDto updateAcademicYearById(UUID academicYearId);

    public abstract AcademicYearDto deleteAcademicYearById(UUID academicYearId);
}
