package com.s2p.util;

import com.s2p.repository.CourseFeeRepository;
import com.s2p.repository.CourseFeeStructureRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

        List<Object[]> expectedFees = courseFeeRepository.findMonthlyExpectedFees();
        List<Object[]> collectedFees = courseFeeStructureRepository.findMonthlyCollectedFees();

        // Initialize with expected fees
        for (Object[] row : expectedFees) {
            int year = (int) row[0];
            int month = (int) row[1];
            double expected = (double) row[2];

            YearMonth ym = YearMonth.of(year, month);
            summary.putIfAbsent(ym, new HashMap<>());
            Map<String, Double> feeData = summary.get(ym);

            feeData.put("expected", expected);
            feeData.put("collected", feeData.getOrDefault("collected", 0.0));
            feeData.put("balance", feeData.get("expected") - feeData.get("collected"));
        }

        // Add collected fees
        for (Object[] row : collectedFees) {
            int year = (int) row[0];
            int month = (int) row[1];
            double collected = (double) row[2];

            YearMonth ym = YearMonth.of(year, month);
            summary.putIfAbsent(ym, new HashMap<>());
            Map<String, Double> feeData = summary.get(ym);

            feeData.put("collected", collected);
            feeData.put("expected", feeData.getOrDefault("expected", 0.0));
            feeData.put("balance", feeData.get("expected") - feeData.get("collected"));
        }

        // Ensure all months have defaults
        for (Map<String, Double> feeData : summary.values()) {
            feeData.putIfAbsent("expected", 0.0);
            feeData.putIfAbsent("collected", 0.0);
            feeData.put("balance", feeData.get("expected") - feeData.get("collected"));
        }

        return summary;
    }
    // Total Fees collected today
    public double getTodayCollectedFees() {
        LocalDate today = LocalDate.now();
        Double total = courseFeeStructureRepository.findTotalCollectedFeesByDate(today);
        return total != null ? total : 0.0;
    }
}