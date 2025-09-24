package com.s2p.master.controller;

import com.s2p.master.dto.BranchDto;
import com.s2p.master.model.Branch;
import com.s2p.master.service.IBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
@Tag(name = "Branch Management",
        description = "APIs for managing branches")
public class BranchController {

    private final IBranchService branchService;

    @PostMapping
    @Operation(summary = "Create a new branch",
            description = "Creates and saves a new branch based on the provided data.")
    public ResponseEntity<Branch> createBranch(@RequestBody BranchDto branchDto) {
        Branch branch = branchService.createBranch(branchDto);
        return ResponseEntity.ok(branch);
    }

    @GetMapping
    @Operation(summary = "Get all branches",
            description = "Retrieves a list of all branches from the system.")
    public ResponseEntity<List<Branch>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get branch by ID",
            description = "Fetches the details of a specific branch using its unique ID.")
    public ResponseEntity<Branch> getBranchById(@PathVariable UUID id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @GetMapping("/by-name/{name}")
    @Operation(summary = "Get branch by name",
            description = "Fetches the branch details using its name.")
    public ResponseEntity<Branch> getBranchByName(@PathVariable String name) {
        return ResponseEntity.ok(branchService.getBranchByName(name));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update branch by ID",
            description = "Updates an existing branch using its unique ID.")
    public ResponseEntity<Branch> updateBranch(@PathVariable UUID id, @RequestBody BranchDto branchDto) {
        Branch updatedBranch = branchService.updateBranch(id, branchDto);
        return ResponseEntity.ok(updatedBranch);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete branch by ID",
            description = "Deletes a branch using its unique ID.")
    public ResponseEntity<String> deleteBranchById(@PathVariable UUID id) {
        branchService.deleteBranchById(id);
        return ResponseEntity.ok("Branch deleted successfully with ID: " + id);
    }

    @DeleteMapping("/by-name/{name}")
    @Operation(summary = "Delete branch by name",
            description = "Deletes a branch using its name.")
    public ResponseEntity<String> deleteBranchByName(@PathVariable String name) {
        branchService.deleteBranchByName(name);
        return ResponseEntity.ok("Branch deleted successfully with name: " + name);
    }
}
