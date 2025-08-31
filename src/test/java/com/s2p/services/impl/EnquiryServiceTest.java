package com.s2p.services.impl;

import com.s2p.dto.EnquiryDto;
import com.s2p.model.Enquiry;
import com.s2p.model.StudentInformation;
import com.s2p.repository.EnquiryRepository;
import com.s2p.util.EnquiryUtility;
import com.s2p.util.EnquiryUtilityImpl;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(AllureJunit5.class)
@Epic("Enquiry Module")   // High-level grouping
@Feature("Enquiry Service")   // Feature under testing
@Slf4j
class EnquiryServiceTest {

    @Mock
    EnquiryRepository enquiryRepository;

    @InjectMocks
    EnquiryService enquiryService;

    @Autowired
    EnquiryUtility enquiryUtility;

    private StudentInformation student1;
    private StudentInformation student2;
    private Enquiry enquiry1;
    private Enquiry enquiry2;

    @BeforeEach
    void setUp() {
        // Initialize mocks for this test class
        MockitoAnnotations.openMocks(this);

        enquiryUtility = Mappers.getMapper(EnquiryUtility.class);

        // Dummy StudentInformation
        this.student1 = new StudentInformation();
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john.doe@example.com");

        this.student2 = new StudentInformation();
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setEmail("jane.smith@example.com");

        // Dummy Enquiry 1
        enquiry1 = new Enquiry();
        enquiry1.setEnquiryId(UUID.randomUUID());
        enquiry1.setEnquiryDate(LocalDate.now());
        enquiry1.setStudentInformation(student1);
        enquiry1.setCourseSet(null);

        // Dummy Enquiry 2
        enquiry2 = new Enquiry();
        enquiry2.setEnquiryId(UUID.randomUUID());
        enquiry2.setEnquiryDate(LocalDate.now());
        enquiry2.setStudentInformation(student1);
        enquiry2.setCourseSet(null);


        enquiryService.enquiryUtility = enquiryUtility;

    }


    @Test
    @Story("Enquiry Management")
    @DisplayName("createEnquiry() — empty test")
    @Description("Placeholder test for EnquiryService.createEnquiry(EnquiryDto)")
    void createEnquiry_success() {
        // Mocking The Behaviour
        when(enquiryRepository.save(any(Enquiry.class))).thenReturn(enquiry2);

        EnquiryDto enquiryDto = enquiryUtility.toEnquiryDto(enquiry1);
        EnquiryDto savedEnquiryDto = enquiryService.createEnquiry(enquiryDto);

        Enquiry enquiry = enquiryUtility.toEnquiryEntity(savedEnquiryDto);

        assertNotNull(enquiry,"Enquiry Should Not Be Null");
        assertEquals(enquiry.getStudentInformation().getStudentInformationId(), enquiry2.getStudentInformation().getStudentInformationId(),"Student Information Id Should Match");
    }

    @Test
    @Story("Enquiry Management")
    @DisplayName("getEnquiryByEmail() — empty test")
    @Description("Placeholder test for EnquiryService.getEnquiryByEmail(String)")
    void getEnquiryByEmail_empty() { }

    @Test
    @Story("Enquiry Management")
    @DisplayName("getAllEnquiries() — empty test")
    @Description("Placeholder test for EnquiryService.getAllEnquiries()")
    void getAllEnquiries_empty() { }

    @Test
    @Story("Enquiry Management")
    @DisplayName("updateEnquiryByEmail() — empty test")
    @Description("Placeholder test for EnquiryService.updateEnquiryByEmail(String, EnquiryDto)")
    void updateEnquiryByEmail_empty() { }

    @Test
    @Story("Enquiry Management")
    @DisplayName("deleteEnquiryByEmail() — empty test")
    @Description("Placeholder test for EnquiryService.deleteEnquiryByEmail(String)")
    void deleteEnquiryByEmail_empty() { }
}