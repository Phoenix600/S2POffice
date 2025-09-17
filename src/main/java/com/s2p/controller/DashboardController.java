package com.s2p.controller;

import com.s2p.util.DashboardUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/dashboard")
public class DashboardController {

    private final DashboardUtility dashboardUtility;

    public DashboardController(DashboardUtility dashboardUtility) {
        this.dashboardUtility = dashboardUtility;
    }

    @GetMapping("/monthly-stats")
    public ResponseEntity<Map<String, Object>> getMonthlyStats() {
        Map<String, Object> response = new HashMap<>();
        response.put("enquiries", dashboardUtility.getMonthlyEnquiryCounts());
        response.put("students", dashboardUtility.getMonthlyStudentCounts());
        response.put("admissions", dashboardUtility.getMonthlyAdmissionCounts());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/average-admissions")
    public ResponseEntity<Map<String, Double>> getAverageAdmissions() {
        Map<String, Double> averages = dashboardUtility.getAverageAdmissionCounts();
        return ResponseEntity.ok(averages);
    }
    @GetMapping("/conversion-rates")
    public ResponseEntity<Map<String, Object>> getConversionRates() {
        return ResponseEntity.ok(dashboardUtility.getConversionRates());
    }


}
