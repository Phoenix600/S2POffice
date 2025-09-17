package com.s2p.util;

import com.s2p.repository.AdmissionRepository;
import com.s2p.repository.EnquiryRepository;
import com.s2p.repository.StudentInformationRepository;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DashboardUtility {

    private final EnquiryRepository enquiryRepository;
    private final StudentInformationRepository studentInformationRepository;
    private final AdmissionRepository admissionRepository;

    public DashboardUtility(EnquiryRepository enquiryRepository,
                            StudentInformationRepository studentInformationRepository,
                            AdmissionRepository admissionRepository) {
        this.enquiryRepository = enquiryRepository;
        this.studentInformationRepository = studentInformationRepository;
        this.admissionRepository = admissionRepository;
    }

    public Map<YearMonth, Long> getMonthlyEnquiryCounts() {
        return convertToMonthlyMap(enquiryRepository.countEnquiriesByMonth());
    }

    public Map<YearMonth, Long> getMonthlyStudentCounts() {
        return convertToMonthlyMap(studentInformationRepository.countStudentsByMonth());
    }

    public Map<YearMonth, Long> getMonthlyAdmissionCounts() {
        return convertToMonthlyMap(admissionRepository.countAdmissionsByMonth());
    }

    private Map<YearMonth, Long> convertToMonthlyMap(List<Object[]> rawResults) {
        Map<YearMonth, Long> result = new HashMap<>();
        for (Object[] row : rawResults) {
            int year = ((Number) row[0]).intValue();
            int month = ((Number) row[1]).intValue();
            long count = ((Number) row[2]).longValue();
            result.put(YearMonth.of(year, month), count);
        }
        return result;
    }
    public Map<String, Double> getAverageAdmissionCounts() {
        Map<YearMonth, Long> monthlyCounts = getMonthlyAdmissionCounts();

        if (monthlyCounts.isEmpty()) {
            Map<String, Double> result = new HashMap<>();
            result.put("averagePerMonth", 0.0);
            result.put("averagePerYear", 0.0);
            return result;
        }

        long totalAdmissions = monthlyCounts.values().stream().mapToLong(Long::longValue).sum();

        double averagePerMonth = (double) totalAdmissions / monthlyCounts.size();

        long distinctYears = monthlyCounts.keySet().stream()
                .map(YearMonth::getYear)
                .distinct()
                .count();

        double averagePerYear = (double) totalAdmissions / distinctYears;

        Map<String, Double> result = new HashMap<>();
        result.put("averagePerMonth", averagePerMonth);
        result.put("averagePerYear", averagePerYear);
        return result;
    }
    public Map<String, Object> getConversionRates() {
        Map<YearMonth, Long> enquiryCounts = getMonthlyEnquiryCounts();
        Map<YearMonth, Long> admissionCounts = getMonthlyAdmissionCounts();

        Map<YearMonth, Double> monthlyConversionRates = new HashMap<>();
        for (YearMonth ym : enquiryCounts.keySet()) {
            long enquiries = enquiryCounts.getOrDefault(ym, 0L);
            long admissions = admissionCounts.getOrDefault(ym, 0L);
            double rate = enquiries == 0 ? 0.0 : (admissions * 100.0) / enquiries;
            monthlyConversionRates.put(ym, rate);
        }

        Map<Integer, Long> yearlyEnquiries = enquiryCounts.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getKey().getYear(),
                        Collectors.summingLong(Map.Entry::getValue)
                ));

        Map<Integer, Long> yearlyAdmissions = admissionCounts.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getKey().getYear(),
                        Collectors.summingLong(Map.Entry::getValue)
                ));

        Map<Integer, Double> yearlyConversionRates = new HashMap<>();
        for (Integer year : yearlyEnquiries.keySet()) {
            long enquiries = yearlyEnquiries.getOrDefault(year, 0L);
            long admissions = yearlyAdmissions.getOrDefault(year, 0L);
            double rate = enquiries == 0 ? 0.0 : (admissions * 100.0) / enquiries;
            yearlyConversionRates.put(year, rate);
        }

        long totalEnquiries = enquiryCounts.values().stream().mapToLong(Long::longValue).sum();
        long totalAdmissions = admissionCounts.values().stream().mapToLong(Long::longValue).sum();
        double overallConversionRate = totalEnquiries == 0 ? 0.0 : (totalAdmissions * 100.0) / totalEnquiries;

        Map<String, Object> result = new HashMap<>();
        result.put("monthlyConversionRates", monthlyConversionRates);
        result.put("yearlyConversionRates", yearlyConversionRates);
        result.put("overallConversionRate", overallConversionRate);

        return result;
    }

}
