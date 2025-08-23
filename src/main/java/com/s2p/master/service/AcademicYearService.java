package com.s2p.master.service;

import com.s2p.master.AcademicYear;
import com.s2p.master.dto.AcademicYearDto;
import com.s2p.master.repository.AcademicYearRepository;
import com.s2p.util.AcademicYearUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class AcademicYearService implements IAcademicYearService{

    @Autowired
    AcademicYearRepository academicYearRepository;

    @Override
    public AcademicYearDto createAcademicYear(AcademicYearDto academicYearDto)
    {
        AcademicYear academicYear = AcademicYearUtility.toAcademicYearEntity(academicYearDto);

        AcademicYear savedAcademicYear = academicYearRepository.save(academicYear);

        AcademicYearDto academicYearDtoResponse = AcademicYearUtility.toAcademicYearDto(savedAcademicYear);

        return academicYearDtoResponse;
    }

    @Override
    public AcademicYearDto getAcademicYearById(UUID academicYearId) {
        return null;
    }

    @Override
    public Set<AcademicYearDto> getAllAcademicYear() {
        return Set.of();
    }

    @Override
    public AcademicYearDto updateAcademicYearById(UUID academicYearId) {
        return null;
    }

    @Override
    public AcademicYearDto deleteAcademicYearById(UUID academicYearId) {
        return null;
    }
}
