package com.s2p.master.service;

import com.s2p.master.dto.AcademicYearDto;
import com.s2p.master.model.AcademicYear;

import java.util.List;
import java.util.UUID;

public interface IAcademicYearService {

    AcademicYear createAcademicYear(AcademicYearDto academicYearDto);

    List<AcademicYear> getAllAcademicYears();

    AcademicYear getAcademicYearById(UUID academicYearId);

    AcademicYear getAcademicYearByValue(Integer academicYear);

    AcademicYear updateAcademicYear(UUID academicYearId, AcademicYearDto academicYearDto);

    void deleteAcademicYearById(UUID academicYearId);

    void deleteAcademicYearByValue(Integer academicYear);
}
