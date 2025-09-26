package com.s2p.services.impl;


import com.s2p.dto.AdmissionDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.services.impl.AdmissionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase // use in-memory H2 DB
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // reset DB after each test
class AdmissionServiceFailureTest {

    @Autowired
    private AdmissionServiceImpl admissionService;

    @Test
    void test_get_admission_by_date_not_found_failure() {
        LocalDate date = LocalDate.of(2025, 1, 1);

        Exception exception = assertThrows(
                ResourceNotFoundException.class,
                () -> admissionService.getAdmissionByDate(date)
        );

        assertEquals("Admission not found for date: " + date, exception.getMessage());
    }

    @Test
    void test_update_admission_by_date_not_found_failure() {
        LocalDate date = LocalDate.of(2025, 2, 1);
        AdmissionDto dummyDto = new AdmissionDto(); // not persisted

        Exception exception = assertThrows(
                ResourceNotFoundException.class,
                () -> admissionService.updateAdmissionByDate(date, dummyDto)
        );

        assertEquals("Admission not found for date: " + date, exception.getMessage());
    }

    @Test
    void test_delete_admission_by_date_not_found_failure() {
        LocalDate date = LocalDate.of(2025, 3, 1);

        Exception exception = assertThrows(
                ResourceNotFoundException.class,
                () -> admissionService.deleteAdmissionByDate(date)
        );

        assertEquals("Admission not found for date: " + date, exception.getMessage());
    }
}
