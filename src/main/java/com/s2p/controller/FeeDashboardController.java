package com.s2p.controller;

import com.s2p.util.FeeDashboardUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.YearMonth;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard/fees")
@RequiredArgsConstructor
@Tag(name = "Fee Dashboard APIs", description = "APIs for fetching fee-related dashboard statistics")
public class FeeDashboardController {

    private final FeeDashboardUtility feeDashboardUtility;

    @Operation(
            summary = "Get Monthly Fee Dashboard",
            description = "Fetch monthly fee summary including collected, pending, and overdue amounts"
    )
    @GetMapping
    public Map<YearMonth, Map<String, Double>> getMonthlyFeeDashboard() {
        return feeDashboardUtility.getMonthlyFeeSummary();
    }
}
