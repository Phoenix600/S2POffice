package com.s2p.services.impl;

import com.s2p.dto.CourseDto;
import com.s2p.dto.EnquiryDto;
import com.s2p.dto.StudentInformationDto;
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
        // 2. Handle Courses (only existing)
        // ----------------------------
        Set<Course> courses = new HashSet<Course>();
        if (enquiryDto.getCourseDtoSet() != null) {
            for (CourseDto courseDto : enquiryDto.getCourseDtoSet()) {
                Optional<Course> existingCourse = courseRepository.findByCourseName(courseDto.getCourseName());
                if (existingCourse.isPresent()) {
                    courses.add(existingCourse.get());
                }
                // If course does not exist, skip it
            }
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


//    @Override
//    public List<EnquiryDto> getEnquiriesByDate(LocalDate date) {
//        List<Enquiry> enquiries = enquiryRepository.findByEnquiryDate(date);
//        List<EnquiryDto> enquiryDtos = new ArrayList<>();
//        for (Enquiry e : enquiries) {
//            enquiryDtos.add(enquiryUtility.toEnquiryDto(e));
//        }
//        return enquiryDtos;
//    }

    @Override
    public List<EnquiryDto> getEnquiriesByDate(LocalDate date) {
        List<Enquiry> enquiries = enquiryRepository.findByEnquiryDate(date);
        List<EnquiryDto> enquiryDtos = new ArrayList<EnquiryDto>();

        for (Enquiry e : enquiries) {
            EnquiryDto dto = enquiryUtility.toEnquiryDto(e);

            // -------------------------------
            // 1. Populate courseDtoSet
            // -------------------------------
            Set<CourseDto> courseDtoSet = new HashSet<CourseDto>();
            Set<Course> courses = e.getCourseSet();
            if (courses != null) {
                for (Course c : courses) {
                    courseDtoSet.add(courseUtility.toCourseDto(c));
                }
            }
            dto.setCourseDtoSet(courseDtoSet);

            // -------------------------------
            // 2. Populate studentInformationDto
            // -------------------------------
            if (e.getStudentInformation() != null) {
                dto.setStudentInformationDto(
                        studentInformationUtility.toStudentInformationDto(e.getStudentInformation())
                );
            }

            enquiryDtos.add(dto);
        }

        return enquiryDtos;
    }

//    @Override
//    public Set<EnquiryDto> getAllEnquiries() {
//        List<Enquiry> enquiries = enquiryRepository.findAll();
//        Set<EnquiryDto> result = new HashSet<>();
//
//        for (Enquiry enquiry : enquiries) {
//            result.add(enquiryUtility.toEnquiryDto(enquiry));
//        }
//
//        return result;
//    }

    @Override
    public Set<EnquiryDto> getAllEnquiries() {
        List<Enquiry> enquiries = enquiryRepository.findAll();
        Set<EnquiryDto> result = new HashSet<EnquiryDto>();

        for (Enquiry enquiry : enquiries) {
            EnquiryDto dto = enquiryUtility.toEnquiryDto(enquiry);

            Set<CourseDto> courseDtoSet = new HashSet<CourseDto>();
            Set<Course> courses = enquiry.getCourseSet();
            if (courses != null) {
                for (Course c : courses) {
                    courseDtoSet.add(courseUtility.toCourseDto(c));
                }
            }
            dto.setCourseDtoSet(courseDtoSet);

            if (enquiry.getStudentInformation() != null) {
                dto.setStudentInformationDto(
                        studentInformationUtility.toStudentInformationDto(enquiry.getStudentInformation())
                );
            }

            result.add(dto);
        }

        return result;
    }

    @Override
    public Optional<EnquiryDto> updateEnquiryByStudentEmail(String email, EnquiryDto enquiryDto) {

        Optional<Enquiry> optionalEnquiry = enquiryRepository.findByStudentInformation_Email(email);

        if (optionalEnquiry.isPresent()) {
            Enquiry existing = optionalEnquiry.get();

            // Update basic fields
            existing.setEnquiryDate(enquiryDto.getEnquiryDate());

            // Map StudentInformationDto to StudentInformation entity
            if (enquiryDto.getStudentInformationDto() != null) {
                existing.setStudentInformation(
                        studentInformationUtility.toStudentInformationEntity(enquiryDto.getStudentInformationDto())
                );
            }

            // Map Set<CourseDto> to Set<Course> entity
            if (enquiryDto.getCourseDtoSet() != null && !enquiryDto.getCourseDtoSet().isEmpty()) {
                Set<Course> courses = new HashSet<Course>();
                for (CourseDto courseDto : enquiryDto.getCourseDtoSet()) {
                    courses.add(courseUtility.toCourseEntity(courseDto));
                }
                existing.setCourseSet(courses);
            }

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
    @Transactional
    public boolean deleteEnquiryByStudentEmail(String email) {
        boolean exists = enquiryRepository.existsByStudentInformation_Email(email);
        if (exists) {
            enquiryRepository.deleteByStudentInformation_Email(email);
            return true;
        }
        return false;
    }
}
