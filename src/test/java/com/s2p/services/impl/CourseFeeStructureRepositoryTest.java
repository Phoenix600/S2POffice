package com.s2p.services.impl;

import com.s2p.model.CourseFeeStructure;
import com.s2p.repository.CourseFeeStructureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CourseFeeStructureRepositoryTest {

    @Autowired
    private CourseFeeStructureRepository repo;

    @Test
    void testFindMonthlyCollectedFees() {
        // arrange → ek sample record insert kar
        CourseFeeStructure cfs = new CourseFeeStructure();
        cfs.setDownPayment(5000.0);
        cfs.setRemainingAmount(15000.0);
        cfs.setIsDiscountGiven(false);
        cfs.setNInstallments((byte)3);
        cfs.setRemainingInstallments((byte)2);

        repo.save(cfs);

        // act
        List<Object[]> results = repo.findMonthlyCollectedFees();

        // assert
        assertFalse(results.isEmpty(), "Results should not be empty");
        results.forEach(row -> {
            System.out.println("Year: " + row[0] + ", Month: " + row[1] + ", Collected: " + row[2]);
        });
    }
}
