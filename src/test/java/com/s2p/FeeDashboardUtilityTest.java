package com.s2p;

import com.s2p.repository.CourseFeeRepository;
import com.s2p.repository.CourseFeeStructureRepository;
import com.s2p.util.FeeDashboardUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FeeDashboardUtilityTest {

    @Mock
    private CourseFeeRepository courseFeeRepository;

    @Mock
    private CourseFeeStructureRepository courseFeeStructureRepository;

    @InjectMocks
    private FeeDashboardUtility feeDashboardUtility;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMonthlyFeeSummary() {
        // Mock data for expected fees
        List<Object[]> expectedFees = new ArrayList<>();
        expectedFees.add(new Object[]{2025, 8, 1000.0});  // Aug 2025 expected fees

        // Mock data for collected fees
        List<Object[]> collectedFees = new ArrayList<>();
        collectedFees.add(new Object[]{2025, 8, 700.0});  // Aug 2025 collected fees

        // Stub repository calls
        when(courseFeeRepository.findMonthlyExpectedFees()).thenReturn(expectedFees);
        when(courseFeeStructureRepository.findMonthlyCollectedFees()).thenReturn(collectedFees);

        // Call method under test
        Map<YearMonth, Map<String, Double>> result = feeDashboardUtility.getMonthlyFeeSummary();

        // Assertions
        YearMonth august2025 = YearMonth.of(2025, 8);
        assertEquals(1, result.size());
        assertEquals(1000.0, result.get(august2025).get("expected"));
        assertEquals(700.0, result.get(august2025).get("collected"));
        assertEquals(300.0, result.get(august2025).get("balance"));  // 1000 - 700
    }

    @Test
    void testGetTodayCollectedFees_WhenDataExists() {
        LocalDate today = LocalDate.now();
        when(courseFeeStructureRepository.findTotalCollectedFeesByDate(today))
                .thenReturn(1500.0);

        double totalCollected = feeDashboardUtility.getTodayCollectedFees();

        assertEquals(1500.0, totalCollected);
    }

    @Test
    void testGetTodayCollectedFees_WhenNoData() {
        LocalDate today = LocalDate.now();
        when(courseFeeStructureRepository.findTotalCollectedFeesByDate(today))
                .thenReturn(null);

        double totalCollected = feeDashboardUtility.getTodayCollectedFees();

        assertEquals(0.0, totalCollected);  // no records → 0.0
    }

    @Test
    void testGetMonthlyFeeSummaryWithNoCollected() {
        // Expected fees only
        List<Object[]> expectedFees = Collections.singletonList(new Object[]{2025, 9, 1200.0});
        when(courseFeeRepository.findMonthlyExpectedFees()).thenReturn(expectedFees);
        when(courseFeeStructureRepository.findMonthlyCollectedFees()).thenReturn(Collections.emptyList());

        Map<YearMonth, Map<String, Double>> result = feeDashboardUtility.getMonthlyFeeSummary();

        YearMonth sept2025 = YearMonth.of(2025, 9);
        assertEquals(1200.0, result.get(sept2025).get("expected"));
        assertEquals(0.0, result.get(sept2025).get("collected"));
        assertEquals(1200.0, result.get(sept2025).get("balance"));
    }

    @Test
    void testGetMonthlyFeeSummaryWithNoExpected() {
        // Collected fees only
        List<Object[]> collectedFees = Collections.singletonList(new Object[]{2025, 10, 500.0});
        when(courseFeeRepository.findMonthlyExpectedFees()).thenReturn(Collections.emptyList());
        when(courseFeeStructureRepository.findMonthlyCollectedFees()).thenReturn(collectedFees);

        Map<YearMonth, Map<String, Double>> result = feeDashboardUtility.getMonthlyFeeSummary();

        YearMonth oct2025 = YearMonth.of(2025, 10);
        assertEquals(0.0, result.get(oct2025).get("expected"));
        assertEquals(500.0, result.get(oct2025).get("collected"));
        assertEquals(-500.0, result.get(oct2025).get("balance"));  // expected - collected
    }
}
