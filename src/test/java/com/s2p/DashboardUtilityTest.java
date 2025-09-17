package com.s2p;

import com.s2p.repository.AdmissionRepository;
import com.s2p.repository.EnquiryRepository;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.util.DashboardUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DashboardUtilityTest {

    @Mock
    private EnquiryRepository enquiryRepository;

    @Mock
    private StudentInformationRepository studentInformationRepository;

    @Mock
    private AdmissionRepository admissionRepository;

    @InjectMocks
    private DashboardUtility dashboardUtility;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMonthlyEnquiryCounts() {
        List<Object[]> mockData = Arrays.asList(
                new Object[]{2025, 1, 10L},
                new Object[]{2025, 2, 15L}
        );
        when(enquiryRepository.countEnquiriesByMonth()).thenReturn(mockData);

        Map<YearMonth, Long> result = dashboardUtility.getMonthlyEnquiryCounts();

        assertEquals(2, result.size());
        assertEquals(10L, result.get(YearMonth.of(2025, 1)));
        assertEquals(15L, result.get(YearMonth.of(2025, 2)));
    }

    @Test
    void testGetMonthlyStudentCounts() {
        List<Object[]> mockData = Arrays.<Object[]>asList(
                new Object[]{2025, 3, 5L}
        );
        when(studentInformationRepository.countStudentsByMonth()).thenReturn(mockData);

        Map<YearMonth, Long> result = dashboardUtility.getMonthlyStudentCounts();

        assertEquals(1, result.size());
        assertEquals(5L, result.get(YearMonth.of(2025, 3)));
    }

    @Test
    void testGetMonthlyAdmissionCounts() {
        List<Object[]> mockData = Arrays.<Object[]>asList(
                new Object[]{2025, 4, 8L}
        );
        when(admissionRepository.countAdmissionsByMonth()).thenReturn(mockData);

        Map<YearMonth, Long> result = dashboardUtility.getMonthlyAdmissionCounts();

        assertEquals(1, result.size());
        assertEquals(8L, result.get(YearMonth.of(2025, 4)));
    }

    @Test
    void testGetAverageAdmissionCounts() {
        List<Object[]> mockData = Arrays.asList(
                new Object[]{2025, 1, 10L},
                new Object[]{2025, 2, 20L},
                new Object[]{2026, 1, 30L}
        );
        when(admissionRepository.countAdmissionsByMonth()).thenReturn(mockData);

        Map<String, Double> result = dashboardUtility.getAverageAdmissionCounts();

        assertEquals(3, mockData.size());
        assertEquals(60.0 / 3, result.get("averagePerMonth"));
        assertEquals(60.0 / 2, result.get("averagePerYear"));
    }

    @Test
    void testGetAverageAdmissionCountsWhenEmpty() {
        when(admissionRepository.countAdmissionsByMonth()).thenReturn(List.of());

        Map<String, Double> result = dashboardUtility.getAverageAdmissionCounts();

        assertEquals(0.0, result.get("averagePerMonth"));
        assertEquals(0.0, result.get("averagePerYear"));
    }
    @Test
    void testGetConversionRates() {
        List<Object[]> enquiryData = Arrays.asList(
                new Object[]{2024, 1, 100L},
                new Object[]{2024, 2, 200L},
                new Object[]{2025, 1, 300L}
        );

        List<Object[]> admissionData = Arrays.asList(
                new Object[]{2024, 1, 10L},
                new Object[]{2024, 2, 20L},
                new Object[]{2025, 1, 60L}
        );

        when(enquiryRepository.countEnquiriesByMonth()).thenReturn(enquiryData);
        when(admissionRepository.countAdmissionsByMonth()).thenReturn(admissionData);

        Map<String, Object> result = dashboardUtility.getConversionRates();

        Map<YearMonth, Double> monthly = (Map<YearMonth, Double>) result.get("monthlyConversionRates");
        Map<Integer, Double> yearly = (Map<Integer, Double>) result.get("yearlyConversionRates");
        double overall = (double) result.get("overallConversionRate");

        assertEquals(10.0, monthly.get(YearMonth.of(2024, 1)));
        assertEquals(10.0, yearly.get(2024));
        assertEquals(20.0, yearly.get(2025)); // 60/300 *100 = 20%
        assertEquals( (10+20+60)*100.0/(100+200+300), overall );
    }
}
