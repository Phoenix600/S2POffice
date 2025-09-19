package com.s2p.services.impl;

import com.s2p.dto.EnquiryDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Enquiry;
import com.s2p.repository.EnquiryRepository;
import com.s2p.repository.specifications.EnquirySpecification;
import com.s2p.services.IEnquiryService;
import com.s2p.util.EnquiryUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public List<EnquiryDto> getEnquiriesByDate(LocalDate date) {
        List<Enquiry> enquiries = enquiryRepository.findByEnquiryDate(date);
        List<EnquiryDto> enquiryDtos = new ArrayList<>();
        for (Enquiry e : enquiries) {
            enquiryDtos.add(enquiryUtility.toEnquiryDto(e));
        }
        return enquiryDtos;
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
    public Optional<EnquiryDto> updateEnquiryByStudentEmail(String email, EnquiryDto enquiryDto) {
        Optional<Enquiry> optionalEnquiry = enquiryRepository.findByStudentInformation_Email(email);
        if (optionalEnquiry.isPresent()) {
            Enquiry existing = optionalEnquiry.get();
            existing.setEnquiryDate(enquiryDto.getEnquiryDate());
            existing.setStudentInformation(enquiryDto.getStudentInformation());
            existing.setCourseSet(enquiryDto.getCourseSet());
            Enquiry updated = enquiryRepository.save(existing);
            return Optional.of(enquiryUtility.toEnquiryDto(updated));
        }
        return Optional.empty();
    }

    @Override
    public Optional<EnquiryDto> getEnquiryByStudentEmail(String email) {
        Optional<Enquiry> optionalEnquiry = enquiryRepository.findByStudentInformation_Email(email);
        if (optionalEnquiry.isPresent()) {
            return Optional.of(enquiryUtility.toEnquiryDto(optionalEnquiry.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteEnquiryByStudentEmail(String email) {
        boolean exists = enquiryRepository.existsByStudentInformation_Email(email);
        if (exists) {
            enquiryRepository.deleteByStudentInformation_Email(email);
            return true;
        }
        return false;
    }

    @Override
    public List<Enquiry> searchEnquiries(String firstName, String email, LocalDate enquiryDate) {
        Specification<Enquiry> spec = Specification
                .anyOf(EnquirySpecification.hasStudentName(firstName))
                .or(EnquirySpecification.hasStudentEmail(email))
                .or(EnquirySpecification.hasEnquiryDate(enquiryDate));

        return enquiryRepository.findAll((Sort) spec);
    }
}
