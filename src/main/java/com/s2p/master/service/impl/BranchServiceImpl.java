package com.s2p.master.service.impl;

import com.s2p.master.dto.BranchDto;
import com.s2p.master.model.Branch;
import com.s2p.master.repository.BranchRepository;
import com.s2p.master.service.IBranchService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements IBranchService {

    private final BranchRepository branchRepository;

    @Override
    public Branch createBranch(BranchDto branchDto) {
        Branch branch = new Branch();
        branch.setBranchName(branchDto.getBranchName());
        return branchRepository.save(branch);
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    @Override
    public Branch getBranchById(UUID branchId) {
        return branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found with ID: " + branchId));
    }

    @Override
    public Branch getBranchByName(String branchName) {
        Branch branch = branchRepository.findByBranchName(branchName);
        if (branch == null) {
            throw new EntityNotFoundException("Branch not found with name: " + branchName);
        }
        return branch;
    }

    @Override
    public Branch updateBranch(UUID branchId, BranchDto branchDto) {
        Branch existingBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found with ID: " + branchId));

        existingBranch.setBranchName(branchDto.getBranchName());
        return branchRepository.save(existingBranch);
    }

    @Override
    public void deleteBranchById(UUID branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found with ID: " + branchId));
        branchRepository.delete(branch);
    }

    @Override
    public void deleteBranchByName(String branchName) {
        Branch branch = branchRepository.findByBranchName(branchName);
        if (branch == null) {
            throw new EntityNotFoundException("Branch not found with name: " + branchName);
        }
        branchRepository.delete(branch);
    }
}
