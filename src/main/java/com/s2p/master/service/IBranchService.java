package com.s2p.master.service;

import com.s2p.master.dto.BranchDto;
import com.s2p.master.model.Branch;

import java.util.List;
import java.util.UUID;

public interface IBranchService {

    Branch createBranch(BranchDto branchDto);

    List<Branch> getAllBranches();

    Branch getBranchById(UUID branchId);

    Branch getBranchByName(String branchName);

    Branch updateBranch(UUID branchId, BranchDto branchDto);

    void deleteBranchById(UUID branchId);

    void deleteBranchByName(String branchName);
}
