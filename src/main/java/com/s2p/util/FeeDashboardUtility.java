package com.s2p.util;

import com.s2p.repository.CourseFeeRepository;
import com.s2p.repository.CourseFeeStructureRepository;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FeeDashboardUtility {

    private final CourseFeeRepository courseFeeRepository;
    private final CourseFeeStructureRepository courseFeeStructureRepository;

    public FeeDashboardUtility(CourseFeeRepository courseFeeRepository,
                               CourseFeeStructureRepository courseFeeStructureRepository) {
        this.courseFeeRepository = courseFeeRepository;
        this.courseFeeStructureRepository = courseFeeStructureRepository;
    }

    public Map<YearMonth, Map<String, Double>> getMonthlyFeeSummary() {
        Map<YearMonth, Map<String, Double>> summary = new HashMap<>();

        // Expected fees
        for (Object[] row : courseFeeRepository.findMonthlyExpectedFees()) {
            int year = ((Number) row[0]).intValue();
            int month = ((Number) row[1]).intValue();
            double expected = ((Number) row[2]).doubleValue();

            YearMonth ym = YearMonth.of(year, month);
            summary.computeIfAbsent(ym, k -> new HashMap<>()).put("expected", expected);
        }

        // Collected fees
        for (Object[] row : courseFeeStructureRepository.findMonthlyCollectedFees()) {
            int year = ((Number) row[0]).intValue();
            int month = ((Number) row[1]).intValue();
            double collected = ((Number) row[2]).doubleValue();

            YearMonth ym = YearMonth.of(year, month);
            summary.computeIfAbsent(ym, k -> new HashMap<>()).put("collected", collected);
        }

        // Balance fees = expected - collected
        for (YearMonth ym : summary.keySet()) {
            Map<String, Double> data = summary.get(ym);
            double expected = data.getOrDefault("expected", 0.0);
            double collected = data.getOrDefault("collected", 0.0);
            data.put("balance", expected - collected);
        }

        return summary;
    }
}