package com.s2p.master.service.impl;

import com.s2p.master.model.College;
import com.s2p.master.repository.CollegeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollegeServiceImplTest {

    @Mock
    private CollegeRepository collegeRepository;

    @InjectMocks
    private CollegeServiceImpl collegeService;

    private College college;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        college = new College();
        college.setCollegeId(UUID.randomUUID());
        college.setCollegeName("Test College");
    }

    @Test
    void testCreateCollege_Success() {
        when(collegeRepository.existsByCollegeName(college.getCollegeName())).thenReturn(false);
        when(collegeRepository.save(college)).thenReturn(college);

        College created = collegeService.createCollege(college);

        assertNotNull(created);
        assertEquals("Test College", created.getCollegeName());
        verify(collegeRepository, times(1)).save(college);
    }

    @Test
    void testCreateCollege_DuplicateName() {
        when(collegeRepository.existsByCollegeName(college.getCollegeName())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> collegeService.createCollege(college));
        verify(collegeRepository, never()).save(any());
    }

    @Test
    void testGetAllColleges() {
        when(collegeRepository.findAll()).thenReturn(Collections.singletonList(college));

        List<College> colleges = collegeService.getAllColleges();

        assertEquals(1, colleges.size());
        assertEquals("Test College", colleges.get(0).getCollegeName());
    }

    @Test
    void testGetCollegeById_Found() {
        when(collegeRepository.findById(college.getCollegeId())).thenReturn(Optional.of(college));

        College found = collegeService.getCollegeById(college.getCollegeId());

        assertNotNull(found);
        assertEquals("Test College", found.getCollegeName());
    }

    @Test
    void testGetCollegeById_NotFound() {
        UUID randomId = UUID.randomUUID();
        when(collegeRepository.findById(randomId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> collegeService.getCollegeById(randomId));
    }

    @Test
    void testGetCollegeByName_Found() {
        when(collegeRepository.findByCollegeName("Test College")).thenReturn(Optional.of(college));

        College found = collegeService.getCollegeByName("Test College");

        assertNotNull(found);
        assertEquals("Test College", found.getCollegeName());
    }

    @Test
    void testGetCollegeByName_NotFound() {
        when(collegeRepository.findByCollegeName("Unknown")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> collegeService.getCollegeByName("Unknown"));
    }

    @Test
    void testUpdateCollege_Success() {
        when(collegeRepository.findById(college.getCollegeId())).thenReturn(Optional.of(college));
        when(collegeRepository.save(any(College.class))).thenReturn(college);

        College updated = new College();
        updated.setCollegeName("Updated College");

        College result = collegeService.updateCollege(college.getCollegeId(), updated);

        assertEquals("Updated College", result.getCollegeName());
    }

    @Test
    void testDeleteCollegeById_Success() {
        when(collegeRepository.existsById(college.getCollegeId())).thenReturn(true);

        collegeService.deleteCollegeById(college.getCollegeId());

        verify(collegeRepository, times(1)).deleteById(college.getCollegeId());
    }

    @Test
    void testDeleteCollegeById_NotFound() {
        UUID randomId = UUID.randomUUID();
        when(collegeRepository.existsById(randomId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> collegeService.deleteCollegeById(randomId));
    }

    @Test
    void testDeleteCollegeByName_Success() {
        when(collegeRepository.existsByCollegeName("Test College")).thenReturn(true);

        collegeService.deleteCollegeByName("Test College");

        verify(collegeRepository, times(1)).deleteByCollegeName("Test College");
    }

    @Test
    void testDeleteCollegeByName_NotFound() {
        when(collegeRepository.existsByCollegeName("Unknown")).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> collegeService.deleteCollegeByName("Unknown"));
    }
}
