package com.s2p.services.impl;

import com.s2p.dto.BatchDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.services.impl.BatchService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase // use in-memory H2 database
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // reset DB after each test
class BatchServiceFailureTest {

    @Autowired
    private BatchService batchService;

    @Test
    void test_get_batch_by_name_not_found_failure() {
        String batchName = "NonExistingBatch";

        Exception exception = assertThrows(
                ResourceNotFoundException.class,
                () -> batchService.getBatchByName(batchName)
        );

        assertEquals("Batch not found with name: " + batchName, exception.getMessage());
    }

    @Test
    void test_update_batch_by_name_not_found_failure() {
        String batchName = "GhostBatch";
        BatchDto dummyDto = new BatchDto();

        Exception exception = assertThrows(
                ResourceNotFoundException.class,
                () -> batchService.updateBatchByName(batchName, dummyDto)
        );

        assertEquals("Batch not found with name: " + batchName, exception.getMessage());
    }

    @Test
    void test_delete_batch_by_name_not_found_failure() {
        String batchName = "PhantomBatch";

        Exception exception = assertThrows(
                ResourceNotFoundException.class,
                () -> batchService.deleteBatchByName(batchName)
        );

        assertEquals("Batch not found with name: " + batchName, exception.getMessage());
    }

    @Test
    void test_create_batch_with_duplicate_name_failure() {
        BatchDto batchDto = new BatchDto();
        batchDto.setBatchName("DuplicateBatch");
        batchDto.setStartTime(LocalTime.of(9, 0));
        batchDto.setEndTime(LocalTime.of(11, 0));

        // First create should pass
        assertDoesNotThrow(() -> batchService.createBatch(batchDto));

        // Second create with same name should fail
        Exception exception = assertThrows(
                BadRequestException.class,
                () -> batchService.createBatch(batchDto)
        );

        assertEquals("Batch already exists with name: " + batchDto.getBatchName(), exception.getMessage());
    }
}
