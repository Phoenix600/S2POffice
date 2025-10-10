package com.s2p.master.controller;

import com.s2p.master.dto.AcademicYearDto;
import com.s2p.master.model.AcademicYear;
import com.s2p.master.service.IAcademicYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.UUID;

@RestController
    @RequestMapping("/api/v1/academic-years")
@RequiredArgsConstructor
@Tag(name = "Academic Year Management",
        description = "APIs for managing academic years")
public class AcademicYearController {

    private final IAcademicYearService academicYearService;

    @PostMapping("/create")
    @Operation(summary = "Create a new academic year",
            description = "Creates and saves a new academic year based on the provided data.")
    //http://localhost:8080/api/v1/academic-years/create
    public ResponseEntity<AcademicYear> createAcademicYear(@RequestBody AcademicYearDto academicYearDto) {
        AcademicYear academicYear = academicYearService.createAcademicYear(academicYearDto);
        return ResponseEntity.ok(academicYear);
    }

    @GetMapping
    @Operation(summary = "Get all academic years",
            description = "Retrieves a list of all academic years from the system.")
    public ResponseEntity<List<AcademicYear>> getAllAcademicYears() {
        return ResponseEntity.ok(academicYearService.getAllAcademicYears());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get academic year by ID",
            description = "Fetches the details of a specific academic year using its unique ID.")
    public ResponseEntity<AcademicYear> getAcademicYearById(@PathVariable UUID id) {
        return ResponseEntity.ok(academicYearService.getAcademicYearById(id));
    }

    @GetMapping("/by-value/{year}")
    @Operation(summary = "Get academic year by value",
            description = "Fetches the academic year details using its year value.")
    public ResponseEntity<AcademicYear> getAcademicYearByValue(@PathVariable Integer year) {
        return ResponseEntity.ok(academicYearService.getAcademicYearByValue(year));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update academic year by ID",
            description = "Updates an existing academic year using its unique ID.")
    public ResponseEntity<AcademicYear> updateAcademicYear(@PathVariable UUID id, @RequestBody AcademicYearDto academicYearDto) {
        AcademicYear updated = academicYearService.updateAcademicYear(id, academicYearDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete academic year by ID",
            description = "Deletes an academic year using its unique ID.")
    public ResponseEntity<String> deleteAcademicYearById(@PathVariable UUID id) {
        academicYearService.deleteAcademicYearById(id);
        return ResponseEntity.ok("Academic Year deleted successfully with ID: " + id);
    }

    @DeleteMapping("/by-value/{year}")
    @Operation(summary = "Delete academic year by value",
            description = "Deletes an academic year using its year value.")
    public ResponseEntity<String> deleteAcademicYearByValue(@PathVariable Integer year) {
        academicYearService.deleteAcademicYearByValue(year);
        return ResponseEntity.ok("Academic Year deleted successfully with value: " + year);
    }
}
