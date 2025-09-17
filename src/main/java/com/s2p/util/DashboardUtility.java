package com.s2p.util;

import com.s2p.repository.AdmissionRepository;
import com.s2p.repository.EnquiryRepository;
import com.s2p.repository.StudentInformationRepository;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // Calculate total admissions
        long totalAdmissions = monthlyCounts.values().stream().mapToLong(Long::longValue).sum();

        // Average per month (total admissions / total months)
        double averagePerMonth = (double) totalAdmissions / monthlyCounts.size();

        // Calculate number of unique years
        long distinctYears = monthlyCounts.keySet().stream()
                .map(YearMonth::getYear)
                .distinct()
                .count();

        // Average per year (total admissions / distinct years)
        double averagePerYear = (double) totalAdmissions / distinctYears;

        Map<String, Double> result = new HashMap<>();
        result.put("averagePerMonth", averagePerMonth);
        result.put("averagePerYear", averagePerYear);
        return result;
    }

}
