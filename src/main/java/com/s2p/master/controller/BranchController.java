package com.s2p.master.controller;

import com.s2p.master.dto.BranchDto;
import com.s2p.master.model.Branch;
import com.s2p.master.service.IBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final IBranchService branchService;

    @PostMapping
    public ResponseEntity<Branch> createBranch(@RequestBody BranchDto branchDto) {
        Branch branch = branchService.createBranch(branchDto);
        return ResponseEntity.ok(branch);
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable UUID id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Branch> getBranchByName(@PathVariable String name) {
        return ResponseEntity.ok(branchService.getBranchByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable UUID id, @RequestBody BranchDto branchDto) {
        Branch updatedBranch = branchService.updateBranch(id, branchDto);
        return ResponseEntity.ok(updatedBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBranchById(@PathVariable UUID id) {
        branchService.deleteBranchById(id);
        return ResponseEntity.ok("Branch deleted successfully with ID: " + id);
    }

    @DeleteMapping("/by-name/{name}")
    public ResponseEntity<String> deleteBranchByName(@PathVariable String name) {
        branchService.deleteBranchByName(name);
        return ResponseEntity.ok("Branch deleted successfully with name: " + name);
    }
}
