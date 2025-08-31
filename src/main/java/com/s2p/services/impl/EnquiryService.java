package com.s2p.services.impl;

import com.s2p.dto.EnquiryDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Enquiry;
import com.s2p.repository.EnquiryRepository;
import com.s2p.services.IEnquiryService;
import com.s2p.util.EnquiryUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnquiryService implements IEnquiryService
{

    @Autowired
    EnquiryRepository enquiryRepository;

    @Autowired
    EnquiryUtility enquiryUtility;

    @Override
    public EnquiryDto createEnquiry(EnquiryDto enquiryDto) {
        Enquiry enquiry = enquiryUtility.toEnquiryEntity(enquiryDto);
        Enquiry saved = enquiryRepository.save(enquiry);
        return enquiryUtility.toEnquiryDto(saved);
    }

    @Override
    public EnquiryDto getEnquiryById(UUID enquiryId) {
        Optional<Enquiry> optionalEnquiry = enquiryRepository.findById(enquiryId);

        if (optionalEnquiry.isEmpty()) {
            throw new ResourceNotFoundException("Enquiry", "id", enquiryId.toString());
        }

        return enquiryUtility.toEnquiryDto(optionalEnquiry.get());
    }

    @Override
    public Set<EnquiryDto> getAllEnquiries() {
        List<Enquiry> enquiries = enquiryRepository.findAll();
        Set<EnquiryDto> result = new HashSet<>();

        for (Enquiry enquiry : enquiries) {
            result.add(enquiryUtility.toEnquiryDto(enquiry));
        }

        return result;
    }

    @Override
    public EnquiryDto partialUpdateEnquiryById(UUID enquiryId) {
        return  null;
    }

    @Override
    public EnquiryDto updateEnquiryById(UUID enquiryId) {
        Optional<Enquiry> optionalEnquiry = enquiryRepository.findById(enquiryId);

        if (optionalEnquiry.isEmpty()) {
            throw new ResourceNotFoundException("Enquiry", "id", enquiryId.toString());
        }

        Enquiry enquiry = optionalEnquiry.get();
        enquiry.setEnquiryDate(optionalEnquiry.get().getEnquiryDate());

        Enquiry updated = enquiryRepository.save(enquiry);
        return enquiryUtility.toEnquiryDto(updated);
    }

    @Override
    public EnquiryDto deleteEnquiryById(UUID enquiryId)
    {
        Optional<Enquiry> optionalEnquiry = enquiryRepository.findById(enquiryId);

        if (optionalEnquiry.isEmpty()) {
            throw new ResourceNotFoundException("Enquiry", "id", enquiryId.toString());
        }

        Enquiry enquiry = optionalEnquiry.get();
        enquiryRepository.delete(enquiry);

        return enquiryUtility.toEnquiryDto(enquiry);
    }
}
