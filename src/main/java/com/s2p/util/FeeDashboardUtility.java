package com.s2p.util;

import com.s2p.repository.CourseFeeRepository;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FeeDashboardUtility {

    private final CourseFeeRepository courseFeesRepository;

    public FeeDashboardUtility(CourseFeeRepository courseFeesRepository) {
        this.courseFeesRepository = courseFeesRepository;
    }

    // Monthly Expected Fees
    public Map<YearMonth, Double> getMonthlyExpectedFees() {
        return convertToMonthlyMap(courseFeesRepository.countExpectedFeesByMonth());
    }

    // Monthly Actual Fees Collected
    public Map<YearMonth, Double> getMonthlyActualFees() {
        return convertToMonthlyMap(courseFeesRepository.countActualFeesByMonth());
    }

    // Monthly Balance Fees = Expected - Actual
    public Map<YearMonth, Double> getMonthlyBalanceFees() {
        Map<YearMonth, Double> expected = getMonthlyExpectedFees();
        Map<YearMonth, Double> actual = getMonthlyActualFees();
        Map<YearMonth, Double> balance = new HashMap<>();

        for (YearMonth ym : expected.keySet()) {
            double expectedAmount = expected.getOrDefault(ym, 0.0);
            double actualAmount = actual.getOrDefault(ym, 0.0);
            balance.put(ym, expectedAmount - actualAmount);
        }
        return balance;
    }

    // Helper method to convert raw repository results to monthly map
    private Map<YearMonth, Double> convertToMonthlyMap(List<Object[]> rawResults) {
        Map<YearMonth, Double> result = new HashMap<>();
        for (Object[] row : rawResults) {
            int year = ((Number) row[0]).intValue();
            int month = ((Number) row[1]).intValue();
            double amount = ((Number) row[2]).doubleValue();
            result.put(YearMonth.of(year, month), amount);
        }
        return result;
    }

    // Average per month and per year for fees
    public Map<String, Double> getAverageFees(Map<YearMonth, Double> monthlyFees) {
        Map<String, Double> result = new HashMap<>();
        if (monthlyFees.isEmpty()) {
            result.put("averagePerMonth", 0.0);
            result.put("averagePerYear", 0.0);
            return result;
        }

        double total = monthlyFees.values().stream().mapToDouble(Double::doubleValue).sum();
        double avgPerMonth = total / monthlyFees.size();
        long distinctYears = monthlyFees.keySet().stream().map(YearMonth::getYear).distinct().count();
        double avgPerYear = total / distinctYears;

        result.put("averagePerMonth", avgPerMonth);
        result.put("averagePerYear", avgPerYear);
        return result;
    }
}
