package com.s2p.master.service.impl;

import com.s2p.master.dto.BranchDto;
import com.s2p.master.model.Branch;
import com.s2p.master.repository.BranchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BranchServiceImplTest {

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchServiceImpl branchService;

    private Branch branch;
    private BranchDto branchDto;
    private UUID branchId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        branchId = UUID.randomUUID();
        branch = new Branch();
        branch.setBranchId(branchId);
        branch.setBranchName("Computer Science");

        branchDto = new BranchDto();
        branchDto.setBranchName("Computer Science");
    }

    @Test
    void testCreateBranch() {
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        Branch savedBranch = branchService.createBranch(branchDto);

        assertNotNull(savedBranch);
        assertEquals("Computer Science", savedBranch.getBranchName());
        verify(branchRepository, times(1)).save(any(Branch.class));
    }

    @Test
    void testGetAllBranches() {
        when(branchRepository.findAll()).thenReturn(List.of(branch));

        List<Branch> branches = branchService.getAllBranches();

        assertEquals(1, branches.size());
        assertEquals("Computer Science", branches.get(0).getBranchName());
        verify(branchRepository, times(1)).findAll();
    }

    @Test
    void testGetBranchById_Found() {
        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branch));

        Branch found = branchService.getBranchById(branchId);

        assertEquals("Computer Science", found.getBranchName());
        verify(branchRepository, times(1)).findById(branchId);
    }

    @Test
    void testGetBranchById_NotFound() {
        when(branchRepository.findById(branchId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> branchService.getBranchById(branchId));
    }

    @Test
    void testGetBranchByName_Found() {
        when(branchRepository.findByBranchName("Computer Science")).thenReturn(branch);

        Branch found = branchService.getBranchByName("Computer Science");

        assertEquals("Computer Science", found.getBranchName());
        verify(branchRepository, times(1)).findByBranchName("Computer Science");
    }

    @Test
    void testGetBranchByName_NotFound() {
        when(branchRepository.findByBranchName("IT")).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> branchService.getBranchByName("IT"));
    }

    @Test
    void testUpdateBranch() {
        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branch));
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        branchDto.setBranchName("IT");
        Branch updated = branchService.updateBranch(branchId, branchDto);

        assertEquals("IT", updated.getBranchName());
        verify(branchRepository, times(1)).save(branch);
    }

    @Test
    void testDeleteBranchById() {
        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branch));

        branchService.deleteBranchById(branchId);

        verify(branchRepository, times(1)).delete(branch);
    }

    @Test
    void testDeleteBranchByName() {
        when(branchRepository.findByBranchName("Computer Science")).thenReturn(branch);

        branchService.deleteBranchByName("Computer Science");

        verify(branchRepository, times(1)).delete(branch);
    }

    @Test
    void testDeleteBranchByName_NotFound() {
        when(branchRepository.findByBranchName("Civil")).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> branchService.deleteBranchByName("Civil"));
    }
}
