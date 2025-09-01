package com.s2p.services.impl;

import com.s2p.dto.BatchDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Batch;
import java.util.List;
import com.s2p.repository.BatchRepository;
import com.s2p.util.BatchUtility;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(AllureJunit5.class)
@Epic("Batch Service Tests")
@Feature("Batch Management")
@Slf4j
class BatchServiceTest {

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private BatchUtility batchUtility;

    @InjectMocks
    private BatchService batchService;

    private AutoCloseable closeable;

    private BatchDto sampleBatchDto;
    private Batch sampleBatch;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        // sample DTO
        sampleBatchDto = new BatchDto();
        sampleBatchDto.setBatchName("Morning Batch");
        sampleBatchDto.setStartTime(LocalTime.of(9, 0));
        sampleBatchDto.setEndTime(LocalTime.of(11, 0));

        // sample Entity
        sampleBatch = new Batch();
        sampleBatch.setBatchName("Morning Batch");
        sampleBatch.setStartTime(LocalTime.of(9, 0));
        sampleBatch.setEndTime(LocalTime.of(11, 0));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @Story("Create new batch")
    @Description("Verify that a new batch can be created successfully when no duplicate exists")
    @DisplayName("Create Batch - Success")
    void testCreateBatch_Success() throws BadRequestException {
        when(batchRepository.findAll()).thenReturn(Collections.emptyList());
        when(batchUtility.toBatchEntity(sampleBatchDto)).thenReturn(sampleBatch);
        when(batchRepository.save(sampleBatch)).thenReturn(sampleBatch);
        when(batchUtility.toBatchDto(sampleBatch)).thenReturn(sampleBatchDto);
        BatchDto result = batchService.createBatch(sampleBatchDto);
        assertNotNull(result);
        assertEquals("Morning Batch", result.getBatchName());
        verify(batchRepository, times(1)).save(sampleBatch);
    }

    @Test
    @Story("Prevent duplicate batches")
    @Description("Ensure creating a batch with duplicate name throws BadRequestException")
    @DisplayName("Create Batch - Duplicate Name Throws Exception")
    void testCreateBatch_Duplicate_ThrowsException() {
        when(batchRepository.findAll()).thenReturn(List.of(sampleBatch));

        assertThrows(BadRequestException.class, () -> batchService.createBatch(sampleBatchDto));
        verify(batchRepository, never()).save(any());
    }

    @Test
    @Story("Fetch batch by name")
    @Description("Verify batch is fetched correctly if exists")
    @DisplayName("Get Batch By Name - Success")
    void testGetBatchByName_Success() {
        when(batchRepository.findByBatchName("Morning Batch")).thenReturn(Optional.of(sampleBatch));
        when(batchUtility.toBatchDto(sampleBatch)).thenReturn(sampleBatchDto);

        BatchDto result = batchService.getBatchByName("Morning Batch");

        assertNotNull(result);
        assertEquals("Morning Batch", result.getBatchName());
    }

    @Test
    @Story("Handle missing batch")
    @Description("Verify exception is thrown if batch is not found")
    @DisplayName("Get Batch By Name - Not Found")
    void testGetBatchByName_NotFound() {
        when(batchRepository.findByBatchName("Unknown")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> batchService.getBatchByName("Unknown"));
    }

    @Test
    @Story("Fetch all batches")
    @Description("Verify that all batches are fetched successfully")
    @DisplayName("Get All Batches - Success")
    void testGetAllBatches() {
        when(batchRepository.findAll()).thenReturn(List.of(sampleBatch));
        when(batchUtility.toBatchDto(sampleBatch)).thenReturn(sampleBatchDto);

        List<BatchDto> result = batchService.getAllBatches();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Morning Batch", result.get(0).getBatchName());
    }

    @Test
    @Story("Update batch by name")
    @Description("Ensure batch can be updated if it exists")
    @DisplayName("Update Batch By Name - Success")
    void testUpdateBatchByName_Success() {
        when(batchRepository.findByBatchName("Morning Batch")).thenReturn(Optional.of(sampleBatch));
        when(batchRepository.save(sampleBatch)).thenReturn(sampleBatch);
        when(batchUtility.toBatchDto(sampleBatch)).thenReturn(sampleBatchDto);

        BatchDto result = batchService.updateBatchByName("Morning Batch", sampleBatchDto);

        assertNotNull(result);
        assertEquals("Morning Batch", result.getBatchName());
        verify(batchRepository, times(1)).save(sampleBatch);
    }

    @Test
    @Story("Handle update for missing batch")
    @Description("Verify exception is thrown if batch to update is not found")
    @DisplayName("Update Batch By Name - Not Found")
    void testUpdateBatchByName_NotFound() {
        when(batchRepository.findByBatchName("Unknown")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> batchService.updateBatchByName("Unknown", sampleBatchDto));
    }

    @Test
    @Story("Delete batch by name")
    @Description("Ensure batch is deleted if it exists")
    @DisplayName("Delete Batch By Name - Success")
    void testDeleteBatchByName_Success() {
        when(batchRepository.findByBatchName("Morning Batch")).thenReturn(Optional.of(sampleBatch));

        batchService.deleteBatchByName("Morning Batch");

        verify(batchRepository, times(1)).delete(sampleBatch);
    }

    @Test
    @Story("Handle delete for missing batch")
    @Description("Verify exception is thrown if batch to delete is not found")
    @DisplayName("Delete Batch By Name - Not Found")
    void testDeleteBatchByName_NotFound() {
        when(batchRepository.findByBatchName("Unknown")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> batchService.deleteBatchByName("Unknown"));
    }

    @Test
    @Story("Search batches")
    @Description("Verify search functionality for batch name, startTime, endTime")
    @DisplayName("Search Batches - By Name/StartTime/EndTime")
    void testSearchBatches() {
        when(batchRepository.findAll(any(Specification.class))).thenReturn(List.of(sampleBatch));
        when(batchUtility.toBatchDto(sampleBatch)).thenReturn(sampleBatchDto);

        List<BatchDto> result = batchService.searchBatches("Morning Batch", LocalTime.of(9, 0), LocalTime.of(11, 0));

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
