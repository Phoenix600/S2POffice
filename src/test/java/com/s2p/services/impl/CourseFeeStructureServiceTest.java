package com.s2p.services.impl;

import com.s2p.dto.CourseFeeStructureDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Course;
import com.s2p.model.CourseFeeStructure;
import com.s2p.model.CourseFees;
import com.s2p.model.StudentUsers;
import com.s2p.repository.CourseFeeStructureRepository;
import com.s2p.util.CourseFeesStructureUtility;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.extern.slf4j.Slf4j;
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
@Epic("Course Fee Module")
@Feature("Course Fee Structure Service")
@Slf4j
class CourseFeeStructureServiceTest {

    @Mock
    private CourseFeeStructureRepository repository;

    @Mock
    private CourseFeesStructureUtility utility;

    @InjectMocks
    private CourseFeeStructureService service;

    private CourseFeeStructure structure1;
    private CourseFeeStructure structure2;
    private CourseFeeStructureDto dto1;
    private CourseFeeStructureDto dto2;
    private Course course;
    private CourseFees courseFees;
    private StudentUsers student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        course = new Course();
        course.setCourseId(UUID.randomUUID());
        course.setCourseName("Java");

        courseFees = new CourseFees();
        courseFees.setCourseFeesID(UUID.randomUUID());
        courseFees.setCourse(course);
        courseFees.setCourseFees(5000.0);

        student = new StudentUsers();
        student.setStudentUserId(UUID.randomUUID());
        student.setEmail("john@example.com");


        structure1 = createStructure(2000.0, 3000.0);
        structure2 = createStructure(2500.0, 2500.0);

        dto1 = createDto(structure1);
        dto2 = createDto(structure2);
    }

    private CourseFeeStructure createStructure(Double downPayment, Double remaining) {
        CourseFeeStructure structure = new CourseFeeStructure();
        structure.setCourseFeeStructureId(UUID.randomUUID());
        structure.setCourse(course);
        structure.setCourseFees(courseFees);
        structure.setStudentUsers(student);
        structure.setDownPayment(downPayment);
        structure.setRemainingAmount(remaining);
        return structure;
    }

    private CourseFeeStructureDto createDto(CourseFeeStructure structure) {
        return new CourseFeeStructureDto(
                structure.getCourseFeeStructureId(),
                structure.getDownPayment(),
                structure.getRemainingAmount(),
                structure.getIsDiscountGiven(),
                structure.getIsDiscountFactor(),
                structure.getNInstallments(),
                structure.getRemainingInstallments(),
                course,
                courseFees,
                student
        );
    }

    @Test
    @Story("Create Course Fee Structure")
    @Description("Verify that a new course fee structure is created successfully")
    @Severity(SeverityLevel.CRITICAL)
    void testCreateCourseFeeStructure_Success() {
        when(utility.toCourseFeeStructureEntity(dto2)).thenReturn(structure2);
        when(repository.save(structure2)).thenReturn(structure2);
        when(utility.toCourseFeeStructureDto(structure2)).thenReturn(dto2);

        CourseFeeStructureDto result = service.createCourseFeeStructure(dto2);

        assertNotNull(result);
        assertEquals(2500.0, result.getDownPayment());
        verify(repository, times(1)).save(structure2);
    }

    @Test
    @Story("Get Fee Structure By Course Name")
    @Severity(SeverityLevel.CRITICAL)
    void testGetFeeStructureByCourseName_Success() {
        when(repository.findByCourse_CourseName("Java")).thenReturn(Optional.of(structure1));
        when(utility.toCourseFeeStructureDto(structure1)).thenReturn(dto1);

        CourseFeeStructureDto result = service.getFeeStructureByCourseName("Java");

        assertNotNull(result);
        assertEquals(2000.0, result.getDownPayment());
    }

    @Test
    @Story("Get All Course Fee Structures")
    @Severity(SeverityLevel.CRITICAL)
    void testGetAllCourseFeeStructures() {
        when(repository.findAll()).thenReturn(List.of(structure1, structure2));
        when(utility.toCourseFeeStructureDto(structure1)).thenReturn(dto1);
        when(utility.toCourseFeeStructureDto(structure2)).thenReturn(dto2);

        Set<CourseFeeStructureDto> result = service.getAllCourseFeeStructures();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @Story("Update Fee Structure By Student Email")
    @Severity(SeverityLevel.CRITICAL)
    void testUpdateFeeStructureByStudentEmail_Success() {
        when(repository.findByStudentUsers_Email(student.getEmail())).thenReturn(Optional.of(structure1));
        when(repository.save(any(CourseFeeStructure.class))).thenReturn(structure1);
        when(utility.toCourseFeeStructureDto(structure1)).thenReturn(dto1);

        dto1.setDownPayment(3000.0);
        CourseFeeStructureDto result = service.updateFeeStructureByStudentEmail(student.getEmail(), dto1);

        assertEquals(3000.0, result.getDownPayment());
        verify(repository, times(1)).save(structure1);
    }

    @Test
    @Story("Update Fee Structure By Student Email Not Found")
    @Severity(SeverityLevel.CRITICAL)
    void testUpdateFeeStructureByStudentEmail_NotFound() {
        when(repository.findByStudentUsers_Email("missing@example.com")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.updateFeeStructureByStudentEmail("missing@example.com", dto1));
    }

    @Test
    @Story("Delete Fee Structure By Student Email")
    @Severity(SeverityLevel.CRITICAL)
    void testDeleteFeeStructureByStudentEmail_Success() {
        when(repository.findByStudentUsers_Email(student.getEmail())).thenReturn(Optional.of(structure1));

        service.deleteFeeStructureByStudentEmail(student.getEmail());

        verify(repository, times(1)).delete(structure1);
    }

    @Test
    @Story("Delete Fee Structure By Student Email Not Found")
    @Severity(SeverityLevel.CRITICAL)
    void testDeleteFeeStructureByStudentEmail_NotFound() {
        when(repository.findByStudentUsers_Email("missing@example.com")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.deleteFeeStructureByStudentEmail("missing@example.com"));
    }
}
