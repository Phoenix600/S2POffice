package com.s2p.controller;

import com.s2p.util.FeeDashboardUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard/fees")
public class FeeDashboardController {

    private final FeeDashboardUtility feeDashboardUtility;

    public FeeDashboardController(FeeDashboardUtility feeDashboardUtility) {
        this.feeDashboardUtility = feeDashboardUtility;
    }

    // Monthly Expected Fees
    @GetMapping("/expected")
    public ResponseEntity<Map<YearMonth, Double>> getMonthlyExpectedFees() {
        Map<YearMonth, Double> data = feeDashboardUtility.getMonthlyExpectedFees();
        return ResponseEntity.ok(data);
    }

    // Monthly Actual Fees Collected
    @GetMapping("/actual")
    public ResponseEntity<Map<YearMonth, Double>> getMonthlyActualFees() {
        Map<YearMonth, Double> data = feeDashboardUtility.getMonthlyActualFees();
        return ResponseEntity.ok(data);
    }

    // Monthly Balance Fees
    @GetMapping("/balance")
    public ResponseEntity<Map<YearMonth, Double>> getMonthlyBalanceFees() {
        Map<YearMonth, Double> data = feeDashboardUtility.getMonthlyBalanceFees();
        return ResponseEntity.ok(data);
    }

    // Average Fees (Expected / Actual / Balance) 
    @GetMapping("/average")
    public ResponseEntity<Map<String, Map<String, Double>>> getAverageFees() {
        Map<String, Double> averageExpected = feeDashboardUtility.getAverageFees(feeDashboardUtility.getMonthlyExpectedFees());
        Map<String, Double> averageActual = feeDashboardUtility.getAverageFees(feeDashboardUtility.getMonthlyActualFees());
        Map<String, Double> averageBalance = feeDashboardUtility.getAverageFees(feeDashboardUtility.getMonthlyBalanceFees());

        Map<String, Map<String, Double>> result = Map.of(
                "expected", averageExpected,
                "actual", averageActual,
                "balance", averageBalance
        );

        return ResponseEntity.ok(result);
    }
}
