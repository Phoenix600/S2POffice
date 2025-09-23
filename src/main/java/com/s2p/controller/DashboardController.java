package com.s2p.controller;

import com.s2p.util.DashboardUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/dashboard")
@Tag(name = "Dashboard APIs", description = "APIs for fetching admin dashboard statistics")
public class DashboardController {

    private final DashboardUtility dashboardUtility;

    public DashboardController(DashboardUtility dashboardUtility) {
        this.dashboardUtility = dashboardUtility;
    }

    @Operation(
            summary = "Get Monthly Statistics",
            description = "Fetch monthly counts for enquiries, students, and admissions"
    )
    @GetMapping("/monthly-stats")
    public ResponseEntity<Map<String, Object>> getMonthlyStats() {
        Map<String, Object> response = new HashMap<>();
        response.put("enquiries", dashboardUtility.getMonthlyEnquiryCounts());
        response.put("students", dashboardUtility.getMonthlyStudentCounts());
        response.put("admissions", dashboardUtility.getMonthlyAdmissionCounts());
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get Average Admissions",
            description = "Retrieve the average number of admissions per month"
    )
    @GetMapping("/average-admissions")
    public ResponseEntity<Map<String, Double>> getAverageAdmissions() {
        Map<String, Double> averages = dashboardUtility.getAverageAdmissionCounts();
        return ResponseEntity.ok(averages);
    }
}
