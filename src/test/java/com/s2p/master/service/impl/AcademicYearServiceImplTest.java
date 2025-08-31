package com.s2p.master.service.impl;

import com.s2p.master.dto.AcademicYearDto;
import com.s2p.master.model.AcademicYear;
import com.s2p.master.repository.AcademicYearRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AcademicYearServiceImplTest {

    @Mock
    private AcademicYearRepository academicYearRepository;

    @InjectMocks
    private AcademicYearServiceImpl academicYearService;

    private AcademicYearDto academicYearDto;
    private AcademicYear academicYear;
    private UUID academicYearId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        academicYearId = UUID.randomUUID();

        academicYearDto = new AcademicYearDto();
        academicYearDto.setAcademicYear(2025);
        academicYearDto.setCourseFees(null);

        academicYear = new AcademicYear();
        academicYear.setAcademicYear(2025);
        academicYear.setCourseFees(null);
    }

    @Test
    void testCreateAcademicYear() {
        when(academicYearRepository.save(any(AcademicYear.class))).thenReturn(academicYear);

        AcademicYear saved = academicYearService.createAcademicYear(academicYearDto);

        assertNotNull(saved);
        assertEquals(2025, saved.getAcademicYear());
        verify(academicYearRepository, times(1)).save(any(AcademicYear.class));
    }

    @Test
    void testGetAllAcademicYears() {
        when(academicYearRepository.findAll()).thenReturn(Collections.singletonList(academicYear));

        List<AcademicYear> result = academicYearService.getAllAcademicYears();

        assertEquals(1, result.size());
        assertEquals(2025, result.get(0).getAcademicYear());
        verify(academicYearRepository, times(1)).findAll();
    }

    @Test
    void testGetAcademicYearById_Found() {
        when(academicYearRepository.findById(academicYearId)).thenReturn(Optional.of(academicYear));

        AcademicYear result = academicYearService.getAcademicYearById(academicYearId);

        assertNotNull(result);
        assertEquals(2025, result.getAcademicYear());
    }

    @Test
    void testGetAcademicYearById_NotFound() {
        when(academicYearRepository.findById(academicYearId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> academicYearService.getAcademicYearById(academicYearId));
    }

    @Test
    void testGetAcademicYearByValue_Found() {
        when(academicYearRepository.findByAcademicYear(2025)).thenReturn(academicYear);

        AcademicYear result = academicYearService.getAcademicYearByValue(2025);

        assertEquals(null, result.getCourseFees());
    }

    @Test
    void testGetAcademicYearByValue_NotFound() {
        when(academicYearRepository.findByAcademicYear(2025)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> academicYearService.getAcademicYearByValue(2025));
    }

    @Test
    void testUpdateAcademicYear_Found() {
        when(academicYearRepository.findById(academicYearId)).thenReturn(Optional.of(academicYear));
        when(academicYearRepository.save(any(AcademicYear.class))).thenReturn(academicYear);

        AcademicYear updated = academicYearService.updateAcademicYear(academicYearId, academicYearDto);

        assertNotNull(updated);
        assertEquals(2025, updated.getAcademicYear());
        assertEquals(null, updated.getCourseFees());
        verify(academicYearRepository, times(1)).save(any(AcademicYear.class));
    }

    @Test
    void testUpdateAcademicYear_NotFound() {
        when(academicYearRepository.findById(academicYearId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> academicYearService.updateAcademicYear(academicYearId, academicYearDto));
    }

    @Test
    void testDeleteAcademicYearById_Found() {
        when(academicYearRepository.findById(academicYearId)).thenReturn(Optional.of(academicYear));

        academicYearService.deleteAcademicYearById(academicYearId);

        verify(academicYearRepository, times(1)).delete(academicYear);
    }

    @Test
    void testDeleteAcademicYearById_NotFound() {
        when(academicYearRepository.findById(academicYearId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> academicYearService.deleteAcademicYearById(academicYearId));
    }

    @Test
    void testDeleteAcademicYearByValue_Found() {
        when(academicYearRepository.findByAcademicYear(2025)).thenReturn(academicYear);

        academicYearService.deleteAcademicYearByValue(2025);

        verify(academicYearRepository, times(1)).delete(academicYear);
    }

    @Test
    void testDeleteAcademicYearByValue_NotFound() {
        when(academicYearRepository.findByAcademicYear(2025)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> academicYearService.deleteAcademicYearByValue(2025));
    }
}
