package com.s2p.services;

import com.s2p.dto.EnquiryDto;

import java.util.Set;
import java.util.UUID;

public interface IEnquiryService
{
    public abstract EnquiryDto createEnquiry(EnquiryDto enquiryDto);

    public abstract EnquiryDto getEnquiryById(UUID enquiryId);

    public abstract Set<EnquiryDto> getAllEnquiries();

    public abstract EnquiryDto partialUpdateEnquiryById(UUID enquiryId);

    public abstract EnquiryDto updateEnquiryById(UUID enquiryId);

    public abstract EnquiryDto deleteEnquiryById(UUID enquiryId);

}
