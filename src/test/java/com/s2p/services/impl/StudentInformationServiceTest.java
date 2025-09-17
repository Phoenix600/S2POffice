package com.s2p.services.impl;

import com.s2p.dto.StudentInformationDto;
import com.s2p.model.StudentInformation;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.util.StudentInformationUtility;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(AllureJunit5.class)
@Epic("Student Module")
@Feature("Student Information Service")
class StudentInformationServiceTest {

    @Mock
    private StudentInformationRepository studentInformationRepository;

    @Mock
    private StudentInformationUtility studentInformationUtility;

    @InjectMocks
    private com.s2p.services.impl.StudentInformationService studentInformationService;

    private StudentInformation studentEntity1;
    private StudentInformation studentEntity2;
    private StudentInformationDto studentDto1;
    private StudentInformationDto studentDto2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        studentEntity1 = new StudentInformation();
        studentEntity1.setStudentInformationId(UUID.randomUUID());
        studentEntity1.setFirstName("Pranay");
        studentEntity1.setLastName("Ramteke");
        studentEntity1.setEmail("pranay@test.com");
        studentEntity1.setCollegeName("ABC College");

        studentEntity2 = new StudentInformation();
        studentEntity2.setStudentInformationId(UUID.randomUUID());
        studentEntity2.setFirstName("John");
        studentEntity2.setLastName("Doe");
        studentEntity2.setEmail("john@test.com");
        studentEntity2.setCollegeName("XYZ College");

        studentDto1 = new StudentInformationDto();
        studentDto1.setStudentInformationId(studentEntity1.getStudentInformationId());
        studentDto1.setFirstName("Pranay");
        studentDto1.setLastName("Ramteke");
        studentDto1.setEmail("pranay@test.com");
        studentDto1.setCollegeName("ABC College");

        studentDto2 = new StudentInformationDto();
        studentDto2.setStudentInformationId(studentEntity2.getStudentInformationId());
        studentDto2.setFirstName("John");
        studentDto2.setLastName("Doe");
        studentDto2.setEmail("john@test.com");
        studentDto2.setCollegeName("XYZ College");
    }

    @Test
    @Story("Create Student")
    @Description("Verify new student creation when email does not exist")
    @Severity(SeverityLevel.CRITICAL)
    void testCreateStudentInformation_Success() throws BadRequestException {
        when(studentInformationRepository.findAll()).thenReturn(Collections.singletonList(studentEntity1));
        when(studentInformationUtility.toStudentInformationEntity(studentDto2)).thenReturn(studentEntity2);
        when(studentInformationRepository.save(studentEntity2)).thenReturn(studentEntity2);
        when(studentInformationUtility.toStudentInformationDto(studentEntity2)).thenReturn(studentDto2);

        StudentInformationDto result = studentInformationService.createStudent(studentDto2);

        assertNotNull(result);
        assertEquals("john@test.com", result.getEmail());
        verify(studentInformationRepository, times(1)).save(studentEntity2);
    }

    @Test
    @Story("Create Student With Duplicate Email")
    @Description("Verify that duplicate student email throws exception")
    @Severity(SeverityLevel.CRITICAL)
    void testCreateStudentInformation_DuplicateEmail() {
        when(studentInformationRepository.findAll()).thenReturn(Collections.singletonList(studentEntity1));

        assertThrows(BadRequestException.class,
                () -> studentInformationService.createStudent(studentDto1));
    }

    @Test
    @Story("Get Student By Email")
    @Description("Verify fetching student by email works")
    void testGetStudentByEmail_Success() {
        when(studentInformationRepository.findByEmail("pranay@test.com"))
                .thenReturn(Optional.of(studentEntity1));
        when(studentInformationUtility.toStudentInformationDto(studentEntity1)).thenReturn(studentDto1);

        Optional<StudentInformationDto> result = Optional.ofNullable(studentInformationService.getStudentByEmail("pranay@test.com"));

        assertTrue(result.isPresent());
        assertEquals("Pranay", result.get().getFirstName());
    }

    @Test
    @Story("Get Student By Email Not Found")
    @Description("Verify fetching student by email returns empty when not found")
    void testGetStudentByEmail_NotFound() {
        when(studentInformationRepository.findByEmail("unknown@test.com"))
                .thenReturn(Optional.empty());

        Optional<StudentInformationDto> result = Optional.ofNullable(studentInformationService.getStudentByEmail("unknown@test.com"));

        assertFalse(result.isPresent());
    }

    @Test
    @Story("Get All Students")
    @Description("Verify all students are returned")
    void testGetAllStudents() {
        when(studentInformationRepository.findAll()).thenReturn(Arrays.asList(studentEntity1, studentEntity2));
        when(studentInformationUtility.toStudentInformationDto(studentEntity1)).thenReturn(studentDto1);
        when(studentInformationUtility.toStudentInformationDto(studentEntity2)).thenReturn(studentDto2);

        Set<StudentInformationDto> result = (Set<StudentInformationDto>) studentInformationService.getAllStudents();

        assertEquals(2, result.size());
        verify(studentInformationRepository, times(1)).findAll();
    }

    @Test
    @Story("Update Student By Email")
    @Description("Verify student update by email works")
    void testUpdateStudentByEmail_Success() {
        when(studentInformationRepository.findByEmail("pranay@test.com")).thenReturn(Optional.of(studentEntity1));
        when(studentInformationRepository.save(any(StudentInformation.class))).thenReturn(studentEntity1);
        when(studentInformationUtility.toStudentInformationDto(studentEntity1)).thenReturn(studentDto1);

        studentDto1.setFirstName("PranayUpdated");
        StudentInformationDto result = studentInformationService.updateStudent("pranay@test.com", studentDto1);

        assertEquals("PranayUpdated", result.getFirstName());
        verify(studentInformationRepository, times(1)).save(studentEntity1);
    }

    @Test
    @Story("Update Student Not Found")
    @Description("Verify updating non-existing student throws exception")
    void testUpdateStudentByEmail_NotFound() {
        when(studentInformationRepository.findByEmail("unknown@test.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> studentInformationService.updateStudent("unknown@test.com", studentDto1));
    }

    @Test
    @Story("Delete Student")
    @Description("Verify student is deleted by email")
    void testDeleteStudentByEmail_Success() {
        when(studentInformationRepository.findByEmail("pranay@test.com")).thenReturn(Optional.of(studentEntity1));

        studentInformationService.deleteStudent("pranay@test.com");

        verify(studentInformationRepository, times(1)).delete(studentEntity1);
    }

    @Test
    @Story("Delete Student Not Found")
    @Description("Verify deleting student with invalid email throws exception")
    void testDeleteStudentByEmail_NotFound() {
        when(studentInformationRepository.findByEmail("unknown@test.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> studentInformationService.deleteStudent("unknown@test.com"));
    }
}
