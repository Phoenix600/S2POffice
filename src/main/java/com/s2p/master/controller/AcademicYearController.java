package com.s2p.master.controller;

import com.s2p.master.dto.AcademicYearDto;
import com.s2p.master.model.AcademicYear;
import com.s2p.master.service.IAcademicYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/academic-years")
@RequiredArgsConstructor
public class AcademicYearController {

    private final IAcademicYearService academicYearService;

    @PostMapping
    public ResponseEntity<AcademicYear> createAcademicYear(@RequestBody AcademicYearDto academicYearDto) {
        AcademicYear academicYear = academicYearService.createAcademicYear(academicYearDto);
        return ResponseEntity.ok(academicYear);
    }

    @GetMapping
    public ResponseEntity<List<AcademicYear>> getAllAcademicYears() {
        return ResponseEntity.ok(academicYearService.getAllAcademicYears());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicYear> getAcademicYearById(@PathVariable UUID id) {
        return ResponseEntity.ok(academicYearService.getAcademicYearById(id));
    }

    @GetMapping("/by-value/{year}")
    public ResponseEntity<AcademicYear> getAcademicYearByValue(@PathVariable Integer year) {
        return ResponseEntity.ok(academicYearService.getAcademicYearByValue(year));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademicYear> updateAcademicYear(@PathVariable UUID id, @RequestBody AcademicYearDto academicYearDto) {
        AcademicYear updated = academicYearService.updateAcademicYear(id, academicYearDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAcademicYearById(@PathVariable UUID id) {
        academicYearService.deleteAcademicYearById(id);
        return ResponseEntity.ok("Academic Year deleted successfully with ID: " + id);
    }

    @DeleteMapping("/by-value/{year}")
    public ResponseEntity<String> deleteAcademicYearByValue(@PathVariable Integer year) {
        academicYearService.deleteAcademicYearByValue(year);
        return ResponseEntity.ok("Academic Year deleted successfully with value: " + year);
    }
}
