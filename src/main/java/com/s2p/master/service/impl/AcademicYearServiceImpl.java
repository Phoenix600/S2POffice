package com.s2p.master.service.impl;

import com.s2p.master.dto.AcademicYearDto;
import com.s2p.master.model.AcademicYear;
import com.s2p.master.repository.AcademicYearRepository;
import com.s2p.master.service.IAcademicYearService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AcademicYearServiceImpl implements IAcademicYearService {

    private final AcademicYearRepository academicYearRepository;

    @Override
    public AcademicYear createAcademicYear(AcademicYearDto academicYearDto) {
        AcademicYear academicYear = new AcademicYear();
        academicYear.setAcademicYear(academicYearDto.getAcademicYear());
        academicYear.setCourseFees(academicYearDto.getCourseFees());
        return academicYearRepository.save(academicYear);
    }

    @Override
    public List<AcademicYear> getAllAcademicYears() {
        return academicYearRepository.findAll();
    }

    @Override
    public AcademicYear getAcademicYearById(UUID academicYearId) {
        return academicYearRepository.findById(academicYearId)
                .orElseThrow(() -> new EntityNotFoundException("Academic Year not found with ID: " + academicYearId));
    }

    @Override
    public AcademicYear getAcademicYearByValue(Integer academicYearValue) {
        AcademicYear academicYear = academicYearRepository.findByAcademicYear(academicYearValue);
        if (academicYear == null) {
            throw new EntityNotFoundException("Academic Year not found with value: " + academicYearValue);
        }
        return academicYear;
    }

    @Override
    public AcademicYear updateAcademicYear(UUID academicYearId, AcademicYearDto academicYearDto) {
        AcademicYear existingAcademicYear = academicYearRepository.findById(academicYearId)
                .orElseThrow(() -> new EntityNotFoundException("Academic Year not found with ID: " + academicYearId));

        existingAcademicYear.setAcademicYear(academicYearDto.getAcademicYear());
        existingAcademicYear.setCourseFees(academicYearDto.getCourseFees());

        return academicYearRepository.save(existingAcademicYear);
    }

    @Override
    public void deleteAcademicYearById(UUID academicYearId) {
        AcademicYear academicYear = academicYearRepository.findById(academicYearId)
                .orElseThrow(() -> new EntityNotFoundException("Academic Year not found with ID: " + academicYearId));
        academicYearRepository.delete(academicYear);
    }

    @Override
    public void deleteAcademicYearByValue(Integer academicYearValue) {
        AcademicYear academicYear = academicYearRepository.findByAcademicYear(academicYearValue);
        if (academicYear == null) {
            throw new EntityNotFoundException("Academic Year not found with value: " + academicYearValue);
        }
        academicYearRepository.delete(academicYear);
    }
}
