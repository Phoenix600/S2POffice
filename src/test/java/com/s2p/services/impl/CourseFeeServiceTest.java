package com.s2p.services.impl;

import com.s2p.dto.CourseFeeDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.master.model.AcademicYear;
import com.s2p.model.Course;
import com.s2p.model.CourseFees;
import com.s2p.repository.CourseFeeRepository;
import com.s2p.util.CourseFeesUtility;
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
@Feature("Course Fee Service")
@Slf4j
class CourseFeeServiceTest {

    @Mock
    private CourseFeeRepository courseFeeRepository;

    @Mock
    private CourseFeesUtility courseFeesUtility;

    @InjectMocks
    private CourseFeeService courseFeeService;

    private CourseFees fee1;
    private CourseFees fee2;
    private CourseFeeDto dto1;
    private CourseFeeDto dto2;
    private Course course;
    private AcademicYear year;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        log.info("CourseFeeServiceTest Setup Called");

        course = new Course();
        course.setCourseId(UUID.randomUUID());
        course.setCourseName("Java");

        fee1 = new CourseFees();
        fee1.setCourseFeesID(UUID.randomUUID());
        fee1.setCourse(course);
        fee1.setAcademicYear(year);
        fee1.setCourseFees(5000.0);

        fee2 = new CourseFees();
        fee2.setCourseFeesID(UUID.randomUUID());
        fee2.setCourse(course);
        fee2.setAcademicYear(year);
        fee2.setCourseFees(7000.0);

        dto1 = new CourseFeeDto(fee1.getCourseFeesID(), 5000.0, null, year, course);
        dto2 = new CourseFeeDto(fee2.getCourseFeesID(), 7000.0, null, year, course);
    }

    @Test
    @Story("Create Course Fee")
    @Description("Verify that a new course fee is created successfully")
    @Severity(SeverityLevel.CRITICAL)
    void testCreateCourseFee_Success() {
        when(courseFeesUtility.toCourseFeeEntity(dto2)).thenReturn(fee2);
        when(courseFeeRepository.save(fee2)).thenReturn(fee2);
        when(courseFeesUtility.toCourseFeeDto(fee2)).thenReturn(dto2);

        CourseFeeDto result = courseFeeService.createCourseFee(dto2);

        assertNotNull(result);
        assertEquals(7000.0, result.getCourseFees());
        verify(courseFeeRepository, times(1)).save(fee2);
    }

    @Test
    @Story("Get Fees By Course Name")
    @Description("Verify fees are returned for an existing course name")
    @Severity(SeverityLevel.CRITICAL)
    void testGetFeesByCourseName_Success() {
        when(courseFeeRepository.findByCourse_CourseName("Java")).thenReturn(List.of(fee1));
        when(courseFeesUtility.toCourseFeeDto(fee1)).thenReturn(dto1);

        List<CourseFeeDto> result = courseFeeService.getFeesByCourseName("Java");

        assertEquals(1, result.size());
        assertEquals(5000.0, result.get(0).getCourseFees());
        verify(courseFeeRepository, times(1)).findByCourse_CourseName("Java");
    }


    @Test
    @Story("Get Fees By Academic Year")
    @Description("Verify fees are returned for an existing academic year")
    @Severity(SeverityLevel.CRITICAL)
    void testGetFeesByAcademicYear_Success() {
        when(courseFeeRepository.findByAcademicYear(year)).thenReturn(List.of(fee1));
        when(courseFeesUtility.toCourseFeeDto(fee1)).thenReturn(dto1);

        List<CourseFeeDto> result = courseFeeService.getFeesByAcademicYear(year);

        assertEquals(1, result.size());
        assertEquals(5000.0, result.get(0).getCourseFees());
        verify(courseFeeRepository, times(1)).findByAcademicYear(year);
    }


    @Test
    @Story("Get All Courses")
    @Description("Verify all course fees are returned")
    @Severity(SeverityLevel.CRITICAL)
    void testGetAllCourses() {
        when(courseFeeRepository.findAll()).thenReturn(List.of(fee1, fee2));
        when(courseFeesUtility.toCourseFeeDto(fee1)).thenReturn(dto1);
        when(courseFeesUtility.toCourseFeeDto(fee2)).thenReturn(dto2);

        Set<CourseFeeDto> result = courseFeeService.getAllCourses();

        assertEquals(2, result.size());
        verify(courseFeeRepository, times(1)).findAll();
    }

    @Test
    @Story("Update Course Fee By Course Name")
    @Description("Verify course fee is updated successfully")
    @Severity(SeverityLevel.CRITICAL)
    void testUpdateCourseFeeByCourseName_Success() {
        when(courseFeeRepository.findByCourse_CourseName("Java")).thenReturn(List.of(fee1));
        when(courseFeeRepository.save(fee1)).thenReturn(fee1);
        when(courseFeesUtility.toCourseFeeDto(fee1)).thenReturn(dto1);

        CourseFeeDto updatedDto = new CourseFeeDto();
        updatedDto.setCourseFees(5000.0);
        updatedDto.setAcademicYear(year);
        updatedDto.setCourse(course);

        CourseFeeDto result = courseFeeService.updateCourseFeeByCourseName("Java", updatedDto);

        assertEquals(5000.0, result.getCourseFees());
        verify(courseFeeRepository, times(1)).save(fee1);
    }



    @Test
    @Story("Delete Course Fees By Course Name")
    @Description("Verify course fees are deleted successfully")
    @Severity(SeverityLevel.CRITICAL)
    void testDeleteCourseFeesByCourseName_Success() {
        when(courseFeeRepository.findByCourse_CourseName("Java")).thenReturn(List.of(fee1));

        String message = courseFeeService.deleteCourseFeesByCourseName("Java");

        assertEquals("Course fees deleted successfully for course: Java", message);
        verify(courseFeeRepository, times(1)).deleteAll(List.of(fee1));
    }

}
