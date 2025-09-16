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
}
