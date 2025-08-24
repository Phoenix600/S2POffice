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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Optional<Admission> optionalAdmission = admissionRepository.findById(admissionId);

        if (optionalAdmission.isEmpty()) {
            throw new ResourceNotFoundException("Admission", "id", admissionId.toString());
        }

        return AdmissionUtility.toAdmissionDto(optionalAdmission.get());
    }

    @Override
    public List<AdmissionDto> getAllAdmissions() {
        List<Admission> admissions = admissionRepository.findAll();
        List<AdmissionDto> result = new ArrayList<>();

        for (Admission admission : admissions) {
            result.add(AdmissionUtility.toAdmissionDto(admission));
        }

        return result;
    }

    @Override
    public AdmissionDto partialUpdateAdmissionById(UUID admissionId) {
        return null;
    }

    @Override
    public AdmissionDto updateAdmissionById(UUID admissionId, AdmissionDto admissionDto) {
        Optional<Admission> optionalAdmission = admissionRepository.findById(admissionId);

        if (optionalAdmission.isEmpty()) {
            throw new ResourceNotFoundException("Admission", "id", admissionId.toString());
        }

        Admission existingAdmission = optionalAdmission.get();
        existingAdmission.setAdmissionDate(admissionDto.getAdmissionDate());

        Admission updatedAdmission = admissionRepository.save(existingAdmission);
        return AdmissionUtility.toAdmissionDto(updatedAdmission);
    }

    @Override
    public AdmissionDto deleteAdmissionById(UUID admissionId) {
        Optional<Admission> optionalAdmission = admissionRepository.findById(admissionId);

        if (optionalAdmission.isEmpty()) {
            throw new ResourceNotFoundException("Admission", "id", admissionId.toString());
        }

        Admission admission = optionalAdmission.get();
        admissionRepository.delete(admission);

        return AdmissionUtility.toAdmissionDto(admission);
    }
}
