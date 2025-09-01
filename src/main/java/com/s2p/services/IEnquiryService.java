package com.s2p.services;

import com.s2p.dto.EnquiryDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IEnquiryService
{
    public abstract EnquiryDto createEnquiry(EnquiryDto enquiryDto);

    public abstract List<EnquiryDto> getEnquiriesByDate(LocalDate date);

    public abstract Set<EnquiryDto> getAllEnquiries();

    public abstract Optional<EnquiryDto> updateEnquiryByStudentEmail(String email, EnquiryDto enquiryDto);

    Optional<EnquiryDto> getEnquiryByStudentEmail(String email);

    public abstract boolean deleteEnquiryByStudentEmail(String email);


}
