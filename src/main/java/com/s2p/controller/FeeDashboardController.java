package com.s2p.controller;

import com.s2p.util.FeeDashboardUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard/fees")
@RequiredArgsConstructor
public class FeeDashboardController {

    private final FeeDashboardUtility feeDashboardUtility;

    @GetMapping
    public Map<YearMonth, Map<String, Double>> getMonthlyFeeDashboard() {
        return feeDashboardUtility.getMonthlyFeeSummary();
    }
}
