package com.s2p.services.impl;

import com.s2p.dto.AdmissionDto;
import com.s2p.model.Admission;
import com.s2p.model.Course;
import com.s2p.repository.AdmissionRepository;
import com.s2p.services.IAdmissionService;
import com.s2p.util.AdmissionUtility;
import com.s2p.util.CourseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdmissionServiceImpl implements IAdmissionService
{
    @Autowired
    AdmissionRepository admissionRepository;

    @Override
    public AdmissionDto createAdmission(AdmissionDto admissionDto)
    {
        Admission admission = AdmissionUtility.toAdmissionEntity(admissionDto);
        Admission savedAdmission = admissionRepository.save(admission);
        AdmissionDto admissionDtoResponse = AdmissionUtility.toAdmissionDto(admission);
        return admissionDtoResponse;
    }

    @Override
    public AdmissionDto getAdmissionById(UUID admissionId)
    {
        Admission admission = admissionRepository.findById(admissionId).orElseThrow(() -> new RuntimeException("Admission Not Found With AdmissionId :- " + admissionId));

        return null;
    }

    @Override
    public List<AdmissionDto> getAllAdmissions() {
        return List.of();
    }

    @Override
    public AdmissionDto partialUpdateAdmissionById(UUID admissionId) {
        return null;
    }

    @Override
    public AdmissionDto updateAdmissionById(UUID admissionId) {
        return null;
    }

    @Override
    public AdmissionDto deleteAdmissionById(UUID admissionId) {
        return null;
    }
}
