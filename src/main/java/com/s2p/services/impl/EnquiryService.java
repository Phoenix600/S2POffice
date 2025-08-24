package com.s2p.services.impl;

import com.s2p.dto.EnquiryDto;
import com.s2p.repository.EnquiryRepository;
import com.s2p.services.IEnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class EnquiryService implements IEnquiryService {

    @Autowired
    EnquiryRepository enquiryRepository;

    @Override
    public EnquiryDto createEnquiry(EnquiryDto enquiryDto) {
        return null;
    }

    @Override
    public EnquiryDto getEnquiryById(UUID enquiryId) {
        return null;
    }

    @Override
    public Set<EnquiryDto> getAllEnquiries() {
        return Set.of();
    }

    @Override
    public EnquiryDto partialUpdateEnquiryById(UUID enquiryId) {
        return null;
    }

    @Override
    public EnquiryDto updateEnquiryById(UUID enquiryId) {
        return null;
    }

    @Override
    public EnquiryDto deleteEnquiryById(UUID enquiryId) {
        return null;
    }
}
