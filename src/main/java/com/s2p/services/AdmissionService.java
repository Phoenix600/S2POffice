package com.s2p.services;

import com.s2p.dto.AdmissionDto;
import com.s2p.repository.AdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdmissionService implements IAdmissionService
{
    @Autowired
    AdmissionRepository admissionRepository;

    @Override
    public AdmissionDto createAdmission(AdmissionDto admissionDto) {
        return null;
    }

    @Override
    public AdmissionDto getAdmissionById(UUID admissionId) {
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
