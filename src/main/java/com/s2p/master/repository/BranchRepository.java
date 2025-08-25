package com.s2p.master.repository;

import com.s2p.master.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
    Branch findByBranchName(String branchName);
}
