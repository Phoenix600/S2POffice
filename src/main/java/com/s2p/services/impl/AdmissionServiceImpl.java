package com.s2p.services.impl;

import com.s2p.dto.AdmissionDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Admission;
import com.s2p.model.Course;
import com.s2p.repository.AdmissionRepository;
import com.s2p.services.IAdmissionService;
import com.s2p.util.AdmissionUtility;
import com.s2p.util.CourseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdmissionServiceImpl implements IAdmissionService
{
    @Autowired
    AdmissionRepository admissionRepository;

    @Autowired
    AdmissionUtility admissionUtility;

    @Override
    public AdmissionDto createAdmission(AdmissionDto admissionDto)
    {
        Admission admission = admissionUtility.toAdmissionEntity(admissionDto);
        Admission savedAdmission = admissionRepository.save(admission);
        AdmissionDto admissionDtoResponse = admissionUtility.toAdmissionDto(admission);
        return admissionDtoResponse;
    }

    @Override
    public AdmissionDto getAdmissionByDate(LocalDate admissionDate) {
        Admission admission = admissionRepository.findByAdmissionDate(admissionDate)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found for date: " + admissionDate));
        return admissionUtility.toAdmissionDto(admission);
    }


    @Override
    public List<AdmissionDto> getAllAdmissions() {
        List<Admission> admissions = admissionRepository.findAll();
        List<AdmissionDto> result = new ArrayList<>();

        for (Admission admission : admissions) {
            result.add(admissionUtility.toAdmissionDto(admission));
        }

        return result;
    }

    @Override
    public AdmissionDto updateAdmissionByDate(LocalDate admissionDate, AdmissionDto admissionDto) {
        Admission admission = admissionRepository.findByAdmissionDate(admissionDate)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found for date: " + admissionDate));

        admission.setAdmissionDate(admissionDto.getAdmissionDate());
        Admission updated = admissionRepository.save(admission);

        return admissionUtility.toAdmissionDto(updated);
    }

    @Override
    public void deleteAdmissionByDate(LocalDate admissionDate) {
        Admission admission = admissionRepository.findByAdmissionDate(admissionDate)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found for date: " + admissionDate));
        admissionRepository.delete(admission);
    }
}
