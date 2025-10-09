package com.s2p.services.impl;

import com.s2p.dto.CourseDto;
import com.s2p.dto.EnquiryDto;
import com.s2p.dto.StudentInformationDto;
import com.s2p.model.Course;
import com.s2p.model.Enquiry;
import com.s2p.model.StudentInformation;
import com.s2p.repository.EnquiryRepository;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.util.EnquiryUtility;
import com.s2p.util.StudentInformationUtility;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@Epic("Enquiry Module")
@Feature("Enquiry Service")
@ExtendWith(AllureJunit5.class)
class EnquiryServiceTest {

    @Mock
    private EnquiryRepository enquiryRepository;

    @Mock
    private StudentInformationRepository studentInformationRepository;

    @Mock
    private EnquiryUtility enquiryUtility;

    @Mock
    private StudentInformationUtility studentInformationUtility;

    @InjectMocks
    private EnquiryService enquiryService;

    private AutoCloseable closeable;

    private StudentInformation student1;
    private StudentInformation student2;
    private Enquiry enquiry1;
    private Enquiry enquiry2;
    private EnquiryDto enquiryDto1;
    private EnquiryDto enquiryDto2;
    private StudentInformationDto studentDto1;
    private StudentInformationDto studentDto2;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        // -------------------------
        // Dummy StudentInformation
        // -------------------------
        student1 = new StudentInformation();
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john.doe@example.com");

        student2 = new StudentInformation();
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setEmail("jane.smith@example.com");

        studentDto1 = new StudentInformationDto();
        studentDto1.setFirstName("John");
        studentDto1.setLastName("Doe");
        studentDto1.setEmail("john.doe@example.com");

        studentDto2 = new StudentInformationDto();
        studentDto2.setFirstName("Jane");
        studentDto2.setLastName("Smith");
        studentDto2.setEmail("jane.smith@example.com");

        // -------------------------
        // Dummy Enquiry
        // -------------------------
        enquiry1 = new Enquiry();
        enquiry1.setEnquiryId(UUID.randomUUID());
        enquiry1.setEnquiryDate(LocalDate.now());
        enquiry1.setStudentInformation(student1);
        enquiry1.setCourseSet(new HashSet<>());

        enquiry2 = new Enquiry();
        enquiry2.setEnquiryId(UUID.randomUUID());
        enquiry2.setEnquiryDate(LocalDate.now());
        enquiry2.setStudentInformation(student2);
        enquiry2.setCourseSet(new HashSet<>());

        // -------------------------
        // Dummy EnquiryDto
        // -------------------------
        enquiryDto1 = new EnquiryDto();
        enquiryDto1.setEnquiryId(enquiry1.getEnquiryId());
        enquiryDto1.setEnquiryDate(enquiry1.getEnquiryDate());
        enquiryDto1.setStudentInformationDto(studentDto1);
        enquiryDto1.setCourseDtoSet(new HashSet<>());

        enquiryDto2 = new EnquiryDto();
        enquiryDto2.setEnquiryId(enquiry2.getEnquiryId());
        enquiryDto2.setEnquiryDate(enquiry2.getEnquiryDate());
        enquiryDto2.setStudentInformationDto(studentDto2);
        enquiryDto2.setCourseDtoSet(new HashSet<>());

        log.info("✅ Test setup complete");
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    // --------------------------------------------------------------
    // Test 1: Create Enquiry
    // --------------------------------------------------------------
    @Test
    @Story("Enquiry Management")
    @DisplayName("createEnquiry() — success test")
    @Description("Verify that enquiry is created successfully")
    void testCreateEnquiry_success() {
        // Mock conversion from DTO to Entity
        when(enquiryUtility.toEnquiryEntity(any(EnquiryDto.class))).thenReturn(enquiry1);

        // Mock StudentInformationRepository call
        when(studentInformationRepository.findByEmail(anyString())).thenReturn(Optional.of(student1));

        // Mock saving entity
        when(enquiryRepository.save(any(Enquiry.class))).thenReturn(enquiry1);

        // Mock conversion from Entity back to DTO
        when(enquiryUtility.toEnquiryDto(any(Enquiry.class))).thenReturn(enquiryDto1);

        // Call service
        EnquiryDto result = enquiryService.createEnquiry(enquiryDto1);

        // Assertions
        assertNotNull(result, "The returned EnquiryDto should not be null");
        assertEquals("john.doe@example.com", result.getStudentInformationDto().getEmail());

        // Verify calls
        verify(enquiryUtility, times(1)).toEnquiryEntity(any(EnquiryDto.class));
        verify(studentInformationRepository, times(1)).findByEmail(anyString());
        verify(enquiryRepository, times(1)).save(any(Enquiry.class));
        verify(enquiryUtility, times(1)).toEnquiryDto(any(Enquiry.class));

        log.info("✅ createEnquiry_success test passed");
    }

    // --------------------------------------------------------------
    // Test 2: Get Enquiry By Email
    // --------------------------------------------------------------
    @Test
    @Story("Enquiry Management")
    @DisplayName("getEnquiryByEmail() — success test")
    @Description("Verify enquiry is fetched correctly by student email")
    void testGetEnquiryByEmail_success() {
        String email = "john.doe@example.com";
        when(enquiryRepository.findByStudentInformation_Email(email)).thenReturn(Optional.of(enquiry1));
        when(enquiryUtility.toEnquiryDto(enquiry1)).thenReturn(enquiryDto1);

        Optional<EnquiryDto> result = enquiryService.getEnquiryByStudentEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getStudentInformationDto().getEmail());
        verify(enquiryRepository, times(1)).findByStudentInformation_Email(email);
    }

    // --------------------------------------------------------------
    // Test 3: Get All Enquiries
    // --------------------------------------------------------------
    @Test
    @Story("Enquiry Management")
    @DisplayName("getAllEnquiries() — success test")
    @Description("Verify all enquiries are returned successfully")
    void testGetAllEnquiries_success() {
        when(enquiryRepository.findAll()).thenReturn(List.of(enquiry1, enquiry2));
        when(enquiryUtility.toEnquiryDto(enquiry1)).thenReturn(enquiryDto1);
        when(enquiryUtility.toEnquiryDto(enquiry2)).thenReturn(enquiryDto2);

        Set<EnquiryDto> result = enquiryService.getAllEnquiries();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(enquiryRepository, times(1)).findAll();
    }

    // --------------------------------------------------------------
    // Test 4: Update Enquiry By Email
    // --------------------------------------------------------------
    @Test
    @Story("Enquiry Management")
    @DisplayName("updateEnquiryByEmail() — success test")
    @Description("Verify that enquiry can be updated successfully by student email")
    void testUpdateEnquiryByEmail_success() {
        String email = "john.doe@example.com";
        when(enquiryRepository.findByStudentInformation_Email(email)).thenReturn(Optional.of(enquiry1));
        when(enquiryRepository.save(any(Enquiry.class))).thenReturn(enquiry1);
        when(enquiryUtility.toEnquiryDto(any(Enquiry.class))).thenReturn(enquiryDto2);

        Optional<EnquiryDto> result = enquiryService.updateEnquiryByStudentEmail(email, enquiryDto2);

        assertTrue(result.isPresent());
        assertEquals("jane.smith@example.com", result.get().getStudentInformationDto().getEmail());
        verify(enquiryRepository, times(1)).save(any(Enquiry.class));
    }

    // --------------------------------------------------------------
    // Test 5: Delete Enquiry By Email
    // --------------------------------------------------------------
    @Test
    @Story("Enquiry Management")
    @DisplayName("deleteEnquiryByEmail() — success test")
    @Description("Verify that enquiry can be deleted successfully by student email")
    void testDeleteEnquiryByEmail_success() {
        String email = "jane.smith@example.com";
        when(enquiryRepository.existsByStudentInformation_Email(email)).thenReturn(true);

        boolean deleted = enquiryService.deleteEnquiryByStudentEmail(email);

        assertTrue(deleted);
        verify(enquiryRepository, times(1)).deleteByStudentInformation_Email(email);
    }
}
