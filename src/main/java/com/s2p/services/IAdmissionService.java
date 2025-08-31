package com.s2p.services;

import com.s2p.dto.AdmissionDto;
import com.s2p.dto.StudentInformationDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IAdmissionService
{
    public abstract AdmissionDto createAdmission(AdmissionDto admissionDto);

    public abstract AdmissionDto getAdmissionByDate(LocalDate admissionDate);

    public abstract List<AdmissionDto> getAllAdmissions();

    public abstract AdmissionDto updateAdmissionByDate(LocalDate admissionDate, AdmissionDto admissionDto);

    public abstract void deleteAdmissionByDate(LocalDate admissionDate);}
