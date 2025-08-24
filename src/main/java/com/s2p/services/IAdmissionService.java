package com.s2p.services;

import com.s2p.dto.AdmissionDto;

import java.util.List;
import java.util.UUID;

public interface IAdmissionService
{
    public abstract AdmissionDto createAdmission(AdmissionDto admissionDto);

    public abstract AdmissionDto getAdmissionById(UUID admissionId);

    public abstract List<AdmissionDto> getAllAdmissions();


    public abstract AdmissionDto partialUpdateAdmissionById(UUID admissionId);

    public abstract AdmissionDto updateAdmissionById(UUID admissionId);

    public abstract AdmissionDto deleteAdmissionById(UUID admissionId);
}
