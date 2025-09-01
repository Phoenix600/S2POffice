package com.s2p.services.impl;

import com.s2p.dto.EnquiryDto;
import com.s2p.model.Course;
import com.s2p.model.Enquiry;
import com.s2p.model.StudentInformation;
import com.s2p.repository.EnquiryRepository;
import com.s2p.util.EnquiryUtility;
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

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    void testCreateEnquiry_success() {
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
    void testGetEnquiryByEmail_success()
    {
        String email = student1.getEmail();
        Optional<Enquiry> optionalEnquiry = Optional.of(enquiry1);

        when(enquiryRepository.findByStudentInformation_Email(email)).thenReturn(optionalEnquiry);

        // Act
        Optional<EnquiryDto> result = enquiryService.getEnquiryByStudentEmail(email);

        // Assert
        assertTrue(result.isPresent(), "Expected enquiry to be found");

        EnquiryDto dto = result.get();
        assertEquals(student1.getEmail(), dto.getStudentInformation().getEmail(), "Student email should match");

        verify(enquiryRepository, times(1)).findByStudentInformation_Email(email);
    }

    @Test
    @Story("Enquiry Management")
    @DisplayName("getAllEnquiries() — empty test")
    @Description("Placeholder test for EnquiryService.getAllEnquiries()")
    void testGetAllEnquiries_success()
    {
        when(enquiryRepository.findAll()).thenReturn(Arrays.asList(enquiry1,enquiry2));

        Set<EnquiryDto> enquiries = enquiryService.getAllEnquiries();

        assertEquals(2,enquiries.size());
        verify(enquiryRepository, times(1)).findAll();
    }

    @Test
    @Story("Enquiry Management")
    @DisplayName("updateEnquiryByEmail() — empty test")
    @Description("Placeholder test for EnquiryService.updateEnquiryByEmail(String, EnquiryDto)")
    void testUpdateEnquiryByEmail_success()
    {
        String email = student1.getEmail();
        Optional<Enquiry> optionalEnquiry = Optional.of(enquiry1);

        when(enquiryRepository.findByStudentInformation_Email(email)).thenReturn(optionalEnquiry);

        EnquiryDto updateDto = new EnquiryDto();
        updateDto.setEnquiryDate(LocalDate.of(2025, 9, 1));
        updateDto.setStudentInformation(student2);
        updateDto.setCourseSet(new HashSet<Course>());

        when(enquiryRepository.save(any(Enquiry.class))).thenReturn(enquiry1);

        // Act
        Optional<EnquiryDto> result = enquiryService.updateEnquiryByStudentEmail(email, updateDto);

        // Assert
        assertTrue(result.isPresent(), "Expected enquiry to be updated");

        EnquiryDto updatedDto = result.get();
        assertEquals(updateDto.getEnquiryDate(), updatedDto.getEnquiryDate(), "Enquiry date should be updated");
        assertEquals(student2.getEmail(), updatedDto.getStudentInformation().getEmail(), "Student email should be updated");

        verify(enquiryRepository, times(1)).findByStudentInformation_Email(email);
        verify(enquiryRepository, times(1)).save(any(Enquiry.class));

    }

    @Test
    @Story("Enquiry Management")
    @DisplayName("deleteEnquiryByEmail() — empty test")
    @Description("Placeholder test for EnquiryService.deleteEnquiryByEmail(String)")
    void testDeleteEnquiryByEmail_success() {

        String email = "jane.smith@example.com";
        when(enquiryRepository.existsByStudentInformation_Email(email)).thenReturn(true);

        // Act
        boolean deleted = enquiryService.deleteEnquiryByStudentEmail(email);

        // Assert
        assertTrue(deleted, "Expected enquiry to be deleted");
        verify(enquiryRepository, times(1)).deleteByStudentInformation_Email(email);

    }
}