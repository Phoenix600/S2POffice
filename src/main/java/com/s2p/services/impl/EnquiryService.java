package com.s2p.services.impl;

import com.s2p.dto.CourseDto;
import com.s2p.dto.EnquiryDto;
import com.s2p.dto.StudentInformationDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Course;
import com.s2p.model.Enquiry;
import com.s2p.model.StudentInformation;
import com.s2p.repository.CourseRepository;
import com.s2p.repository.EnquiryRepository;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.services.IEnquiryService;
import com.s2p.util.CourseUtility;
import com.s2p.util.EnquiryUtility;
import com.s2p.util.StudentInformationUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

@Service
public class EnquiryService implements IEnquiryService
{

    @Autowired
    EnquiryRepository enquiryRepository;

    @Autowired
    EnquiryUtility enquiryUtility;

    @Autowired
    StudentInformationUtility studentInformationUtility;

    @Autowired
    CourseUtility courseUtility;

    @Autowired
    StudentInformationRepository studentInformationRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    @Transactional
    public EnquiryDto createEnquiry(EnquiryDto enquiryDto) {
        // Convert DTO -> Entity
        Enquiry enquiry = enquiryUtility.toEnquiryEntity(enquiryDto);

        // ----------------------------
        // 1. Handle Student Information
        // ----------------------------
        StudentInformationDto studentDto = enquiryDto.getStudentInformationDto();
        StudentInformation student = studentInformationRepository.findByEmail(studentDto.getEmail())
                .orElseGet(() -> studentInformationRepository.save(
                        studentInformationUtility.toStudentInformationEntity(studentDto)
                ));
        enquiry.setStudentInformation(student);

        // ----------------------------
        // 2. Handle Courses
        // ----------------------------
        Set<Course> courses = new HashSet<>();
        for (CourseDto courseDto : enquiryDto.getCourseDtoSet()) {
            Course course;

            Optional<Course> existingCourse = courseRepository.findByCourseName(courseDto.getCourseName());
            if (existingCourse.isPresent()) {
                course = existingCourse.get();
            } else {
                course = courseUtility.toCourseEntity(courseDto);
                course = courseRepository.save(course);
            }

            courses.add(course);
        }
        enquiry.setCourseSet(courses);

        // ----------------------------
        // 3. Save Enquiry
        // ----------------------------
        Enquiry saved = enquiryRepository.save(enquiry);

        // ----------------------------
        // 4. Return DTO
        // ----------------------------
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
            existing.setStudentInformation(enquiryDto.getStudentInformationDto());
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
}
