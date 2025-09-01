package com.s2p.services.impl;

import com.s2p.dto.CourseFeeDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.CourseFees;
import com.s2p.repository.CourseFeeRepository;
import com.s2p.util.CourseFeesUtility;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@Epic("Course Fee Management")
@Feature("CourseFeeService Unit Tests")
@ExtendWith(AllureJunit5.class)
class CourseFeeServiceTest {

    @Mock
    private CourseFeeRepository courseFeeRepository;

    @Mock
    private CourseFeesUtility courseFeesUtility;

    @InjectMocks
    private CourseFeeService courseFeeService;

    private UUID courseFeeId;
    private CourseFees courseFees;
    private CourseFeeDto courseFeeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        courseFeeId = UUID.randomUUID();

        courseFees = new CourseFees();
        courseFees.setCourseFeesID(courseFeeId);
        courseFees.setCourseFees(15000.0);

        courseFeeDto = new CourseFeeDto();
        courseFeeDto.setCourseFeesID(courseFeeId);
        courseFeeDto.setCourseFees(15000.0);
    }

    @Test
    @Story("Create new course fee")
    @Description("Verify that a new course fee is created successfully")
    @DisplayName("Create Course Fee - Success")
    void testCreateCourseFee() {
        when(courseFeesUtility.toCourseFeeEntity(any(CourseFeeDto.class))).thenReturn(courseFees);
        when(courseFeeRepository.save(any(CourseFees.class))).thenReturn(courseFees);
        when(courseFeesUtility.toCourseFeeDto(any(CourseFees.class))).thenReturn(courseFeeDto);

        CourseFeeDto result = courseFeeService.createCourseFee(courseFeeDto);

        assertThat(result).isNotNull();
        assertThat(result.getCourseFees()).isEqualTo(15000.0);
        verify(courseFeeRepository, times(1)).save(any(CourseFees.class));
        log.info("testCreateCourseFee passed");
    }

    @Test
    @Story("Fetch course fee by ID")
    @Description("Verify that course fee is fetched when ID exists")
    @DisplayName("Get Course Fee By ID - Found")
    void testGetCourseFeeById_WhenFound() {
        when(courseFeeRepository.findById(courseFeeId)).thenReturn(Optional.of(courseFees));
        when(courseFeesUtility.toCourseFeeDto(courseFees)).thenReturn(courseFeeDto);

        CourseFeeDto result = courseFeeService.getCourseFeeById(courseFeeId);

        assertThat(result).isNotNull();
        assertThat(result.getCourseFeesID()).isEqualTo(courseFeeId);
        log.info("testGetCourseFeeById_WhenFound passed");
    }

    @Test
    @Story("Fetch course fee by ID")
    @Description("Verify exception is thrown when course fee ID does not exist")
    @DisplayName("Get Course Fee By ID - Not Found")
    void testGetCourseFeeById_WhenNotFound() {
        when(courseFeeRepository.findById(courseFeeId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> courseFeeService.getCourseFeeById(courseFeeId));

        log.info("testGetCourseFeeById_WhenNotFound passed");
    }

    @Test
    @Story("Fetch all course fees")
    @Description("Verify that all course fees are returned from repository")
    @DisplayName("Get All Course Fees - Success")
    void testGetAllCourses() {
        List<CourseFees> feesList = List.of(courseFees);
        when(courseFeeRepository.findAll()).thenReturn(feesList);
        when(courseFeesUtility.toCourseFeeDto(courseFees)).thenReturn(courseFeeDto);

        Set<CourseFeeDto> result = courseFeeService.getAllCourses();

        assertThat(result).hasSize(1);
        assertThat(result.iterator().next().getCourseFees()).isEqualTo(15000.0);
        log.info("testGetAllCourses passed");
    }

    @Test
    @Story("Update course fee")
    @Description("Verify that existing course fee is updated when ID is valid")
    @DisplayName("Update Course Fee - Success")
    void testUpdateCourseFeeById_WhenFound() {
        when(courseFeeRepository.findById(courseFeeId)).thenReturn(Optional.of(courseFees));
        when(courseFeeRepository.save(any(CourseFees.class))).thenReturn(courseFees);
        when(courseFeesUtility.toCourseFeeDto(courseFees)).thenReturn(courseFeeDto);

        CourseFeeDto result = courseFeeService.updateCourseFeeById(courseFeeId, courseFeeDto);

        assertThat(result).isNotNull();
        assertThat(result.getCourseFees()).isEqualTo(15000.0);
        verify(courseFeeRepository, times(1)).save(courseFees);
        log.info("testUpdateCourseFeeById_WhenFound passed");
    }

    @Test
    @Story("Update course fee")
    @Description("Verify exception is thrown when updating with invalid ID")
    @DisplayName("Update Course Fee - Not Found")
    void testUpdateCourseFeeById_WhenNotFound() {
        when(courseFeeRepository.findById(courseFeeId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> courseFeeService.updateCourseFeeById(courseFeeId, courseFeeDto));

        log.info("testUpdateCourseFeeById_WhenNotFound passed");
    }

    @Test
    @Story("Delete course fee")
    @Description("Verify that course fee is deleted when ID exists")
    @DisplayName("Delete Course Fee - Success")
    void testDeleteCourseFeeById_WhenFound() {
        when(courseFeeRepository.findById(courseFeeId)).thenReturn(Optional.of(courseFees));
        when(courseFeesUtility.toCourseFeeDto(courseFees)).thenReturn(courseFeeDto);

        CourseFeeDto result = courseFeeService.deleteCourseFeeById(courseFeeId);

        assertThat(result).isNotNull();
        assertThat(result.getCourseFeesID()).isEqualTo(courseFeeId);
        verify(courseFeeRepository, times(1)).delete(courseFees);
        log.info("testDeleteCourseFeeById_WhenFound passed");
    }

    @Test
    @Story("Delete course fee")
    @Description("Verify exception is thrown when deleting with invalid ID")
    @DisplayName("Delete Course Fee - Not Found")
    void testDeleteCourseFeeById_WhenNotFound() {
        when(courseFeeRepository.findById(courseFeeId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> courseFeeService.deleteCourseFeeById(courseFeeId));

        log.info("testDeleteCourseFeeById_WhenNotFound passed");
    }
}
