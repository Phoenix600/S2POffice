package com.s2p.services.impl;

import com.s2p.dto.CourseDto;
import com.s2p.exceptions.AlreadyExistsException;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Course;
import com.s2p.repository.CourseRepository;
import com.s2p.util.CourseUtility;
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
@Epic("Course Module")
@Feature("Course Service")
@Slf4j
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseUtility courseUtility;

    @InjectMocks
    private CourseService courseService;

    private Course course;
    private CourseDto courseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        course = new Course();
        course.setCourseId(UUID.randomUUID());
        course.setCourseName("Java");
        course.setDescription("Java Programming");
        course.setCourseDurationInMonths((byte) 6);

        courseDto = new CourseDto();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setDescription(course.getDescription());
        courseDto.setCourseDurationInMonths(course.getCourseDurationInMonths());
    }

    @Test
    @Story("Create Course")
    @Severity(SeverityLevel.CRITICAL)
    void testCreateCourse_Success() {
        when(courseRepository.existsByCourseName(courseDto.getCourseName())).thenReturn(false);
        when(courseUtility.toCourseEntity(courseDto)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(course);
        when(courseUtility.toCourseDto(course)).thenReturn(courseDto);

        CourseDto result = courseService.createCourse(courseDto);

        assertNotNull(result);
        assertEquals("Java", result.getCourseName());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    @Story("Create Course Already Exists")
    @Severity(SeverityLevel.CRITICAL)
    void testCreateCourse_AlreadyExists() {
        when(courseRepository.existsByCourseName(courseDto.getCourseName())).thenReturn(true);

        assertThrows(AlreadyExistsException.class,
                () -> courseService.createCourse(courseDto));
    }

    @Test
    @Story("Get Course By Name")
    @Severity(SeverityLevel.CRITICAL)
    void testGetCourseByName_Success() {
        when(courseRepository.findByCourseName("Java")).thenReturn(Optional.of(course));
        when(courseUtility.toCourseDto(course)).thenReturn(courseDto);

        CourseDto result = courseService.getCourseByName("Java");

        assertNotNull(result);
        assertEquals("Java", result.getCourseName());
    }


    @Test
    @Story("Get All Courses")
    @Severity(SeverityLevel.CRITICAL)
    void testGetAllCourses_Success() {
        List<Course> courses = List.of(course);
        when(courseRepository.findAll()).thenReturn(courses);
        when(courseUtility.toCourseDto(course)).thenReturn(courseDto);

        List<CourseDto> result = courseService.getAllCourses();

        assertEquals(1, result.size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    @Story("Update Course By Name Not Found")
    @Severity(SeverityLevel.CRITICAL)
    void testUpdateCourseByName_NotFound() {
        when(courseRepository.findByCourseName("Python")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> courseService.updateCourseByName("Python", courseDto));
    }

    @Test
    @Story("Delete Course By Name")
    @Severity(SeverityLevel.CRITICAL)
    void testDeleteCourseByName_Success() {
        when(courseRepository.findByCourseName("Java")).thenReturn(Optional.of(course));

        courseService.deleteCourseByName("Java");

        verify(courseRepository, times(1)).delete(course);
    }

    @Test
    @Story("Delete Course By Name Not Found")
    @Severity(SeverityLevel.CRITICAL)
    void testDeleteCourseByName_NotFound() {
        when(courseRepository.findByCourseName("Python")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> courseService.deleteCourseByName("Python"));
    }

    @Test
    @Story("Search Courses (Stub)")
    @Severity(SeverityLevel.NORMAL)
    void testSearchCourses() {
        List<CourseDto> result = courseService.searchCourses("Java", "Programming", (byte) 6);
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
