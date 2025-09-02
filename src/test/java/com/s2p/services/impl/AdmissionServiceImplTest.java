package com.s2p.services.impl;


import com.s2p.dto.AdmissionDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Admission;
import com.s2p.repository.AdmissionRepository;
import com.s2p.util.AdmissionUtility;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(AllureJunit5.class)
@Epic("Admission Module")   // High-level grouping
@Feature("Admission Service")   // Feature under testing
@Slf4j
class AdmissionServiceImplTest {

    @Mock
    private AdmissionRepository admissionRepository;

    @Mock
    private AdmissionUtility admissionUtility;

    @InjectMocks
    private AdmissionServiceImpl admissionService;

    private Admission admission;
    private AdmissionDto admissionDto;
    private UUID admissionId;
    private LocalDate admissionDate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        admissionId = UUID.randomUUID();
        admissionDate = LocalDate.of(2025, 1, 1);

        admission = new Admission();
        admission.setAdmissionId(admissionId);
        admission.setAdmissionDate(admissionDate);

        admissionDto = new AdmissionDto(admissionId, admissionDate);
    }

    @Test
    @Story(value = "Create Country")// Describes the user story
    @DisplayName("testCreateAdmission -success test")
    @Description("Placeholder test for AdmissionService.testCreateAdimmison ")

    void testCreateAdmission_success() {
        when(admissionUtility.toAdmissionEntity(admissionDto)).thenReturn(admission);
        when(admissionRepository.save(admission)).thenReturn(admission);
        when(admissionUtility.toAdmissionDto(admission)).thenReturn(admissionDto);

        AdmissionDto result = admissionService.createAdmission(admissionDto);

        assertNotNull(result);
        assertEquals(admissionId, result.getAdmissionId());
        verify(admissionRepository, times(1)).save(admission);
    }

    @Test
    @Story(value = "Create Country")// Describes the user story
    @DisplayName("testCreateAdmission -success test")
    @Description("Placeholder test for AdmissionService.testCreateAdimmison ")

    void testGetAdmissionByDate_Found() {
        when(admissionRepository.findByAdmissionDate(admissionDate)).thenReturn(Optional.of(admission));
        when(admissionUtility.toAdmissionDto(admission)).thenReturn(admissionDto);

        AdmissionDto result = admissionService.getAdmissionByDate(admissionDate);

        assertNotNull(result);
        assertEquals(admissionId, result.getAdmissionId());
        verify(admissionRepository, times(1)).findByAdmissionDate(admissionDate);
    }

    @Test
    @Story(value = "Create Country")// Describes the user story
    @DisplayName("testCreateAdmission -success test")
    @Description("Placeholder test for AdmissionService.testCreateAdimmison ")

    void testGetAdmissionByDate_NotFound() {
        when(admissionRepository.findByAdmissionDate(admissionDate)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> admissionService.getAdmissionByDate(admissionDate));
    }

    @Test
    @Story(value = "Create Country")// Describes the user story
    @DisplayName("testCreateAdmission -success test")
    @Description("Placeholder test for AdmissionService.testCreateAdimmison ")

    void testGetAllAdmissions() {
        Admission admission2 = new Admission(UUID.randomUUID(), LocalDate.of(2025, 2, 2));
        AdmissionDto admissionDto2 = new AdmissionDto(admission2.getAdmissionId(), admission2.getAdmissionDate());

        when(admissionRepository.findAll()).thenReturn(Arrays.asList(admission, admission2));
        when(admissionUtility.toAdmissionDto(admission)).thenReturn(admissionDto);
        when(admissionUtility.toAdmissionDto(admission2)).thenReturn(admissionDto2);

        List<AdmissionDto> result = admissionService.getAllAdmissions();

        assertEquals(2, result.size());
        verify(admissionRepository, times(1)).findAll();
    }

    @Test
    @Story(value = "Create Country")// Describes the user story
    @DisplayName("testCreateAdmission -success test")
    @Description("Placeholder test for AdmissionService.testCreateAdimmison ")

    void testUpdateAdmissionByDate_Found() {
        LocalDate newDate = LocalDate.of(2025, 3, 3);
        AdmissionDto updatedDto = new AdmissionDto(admissionId, newDate);

        when(admissionRepository.findByAdmissionDate(admissionDate)).thenReturn(Optional.of(admission));
        when(admissionRepository.save(admission)).thenReturn(admission);
        when(admissionUtility.toAdmissionDto(admission)).thenReturn(updatedDto);

        AdmissionDto result = admissionService.updateAdmissionByDate(admissionDate, updatedDto);

        assertNotNull(result);
        assertEquals(newDate, result.getAdmissionDate());
        verify(admissionRepository, times(1)).save(admission);
    }

    @Test
    @Story(value = "Create Country")// Describes the user story
    @DisplayName("testCreateAdmission -success test")
    @Description("Placeholder test for AdmissionService.testCreateAdimmison ")

    void testUpdateAdmissionByDate_NotFound() {
        when(admissionRepository.findByAdmissionDate(admissionDate)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> admissionService.updateAdmissionByDate(admissionDate, admissionDto));
    }

    @Test
    @Story(value = "Create Country")// Describes the user story
    @DisplayName("testCreateAdmission -success test")
    @Description("Placeholder test for AdmissionService.testCreateAdimmison ")

    void testDeleteAdmissionByDate_Found() {
        when(admissionRepository.findByAdmissionDate(admissionDate)).thenReturn(Optional.of(admission));

        admissionService.deleteAdmissionByDate(admissionDate);

        verify(admissionRepository, times(1)).delete(admission);
    }

    @Test
    @Story(value = "Create Country")// Describes the user story
    @DisplayName("testCreateAdmission -success test")
    @Description("Placeholder test for AdmissionService.testCreateAdimmison ")

    void testDeleteAdmissionByDate_NotFound() {
        when(admissionRepository.findByAdmissionDate(admissionDate)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> admissionService.deleteAdmissionByDate(admissionDate));
    }
}
