package com.s2p.junitTesting;

import com.s2p.dto.CourseDto;
import com.s2p.dto.EnquiryDto;
import com.s2p.dto.StudentInformationDto;
import com.s2p.model.StudentInformation;
import com.s2p.services.IEnquiryService;
import com.s2p.util.StudentInformationUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class IEnquiryServiceTest {

    @Autowired
    IEnquiryService enquiryService;

    @Autowired
    StudentInformationUtility studentInformationUtility;

    @Test
    void test_createEnquiry_success()
    {
        EnquiryDto enquiryDto = new EnquiryDto();
        enquiryDto.setEnquiryDate(LocalDate.now());

        // Create and set Student Information
        StudentInformationDto studentInformationDto = new StudentInformationDto();
        studentInformationDto.setFirstName("Harsh");
        studentInformationDto.setLastName("Diwathe");
        studentInformationDto.setEmail("harsh.diwathe@example.com");
        studentInformationDto.setCollegeName("JD College");
        studentInformationDto.setDepartName("Artificial Intelligence");
        studentInformationDto.setSemester("8th");
        studentInformationDto.setPassingYear("2025");

        StudentInformation studentInformation =
                studentInformationUtility.toStudentInformationEntity(studentInformationDto);

        // Now set into enquiryDto
        enquiryDto.setStudentInformation(studentInformation);

        // Create and set Course
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Java Full Stack Development");
        courseDto.setDescription("End-to-end full stack mastery");
        courseDto.setCourseDurationInMonths((byte) 6);
    }

    @Test
    void getEnquiriesByDate() {
    }

    @Test
    void getAllEnquiries() {
    }

    @Test
    void updateEnquiryByStudentEmail() {
    }

    @Test
    void getEnquiryByStudentEmail() {
    }

    @Test
    void deleteEnquiryByStudentEmail() {
    }
}