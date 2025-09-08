package com.s2p.services.impl;


import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.services.impl.CourseFeeInstallmentTransactionsServiceimpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase // Uses in-memory H2 database for tests
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Reset DB after each test
class CourseFeeInstallmentTransactionsServiceFailureTest {

    @Autowired
    private CourseFeeInstallmentTransactionsServiceimpl service;

    @Test
    void test_get_transactions_by_course_name_when_no_data_failure() {
        String courseName = "NonExistingCourse";

        Exception exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.getTransactionsByCourseName(courseName)
        );

        assertEquals("No transactions available", exception.getMessage());
    }
}
