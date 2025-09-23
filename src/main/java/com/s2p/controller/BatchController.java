package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.BatchDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.BatchService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/batch")
@Tag(name = "Batch Management APIs", description = "CRUD operations and search functionality for Batches")
public class BatchController {

    @Autowired
    BatchService batchService;

    @Operation(summary = "Create Batch", description = "Create a new batch with details provided in the request body")
    @PostMapping("/create-batch")
    public ResponseEntity<ApiResponseDto<BatchDto>> createBatch(@Valid @RequestBody BatchDto batchDto) throws BadRequestException {
        BatchDto createdBatch = batchService.createBatch(batchDto);

        ApiResponseDto<BatchDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(createdBatch);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Batch by Name", description = "Retrieve details of a batch by its name")
    @GetMapping("/{batchName}")
    public ResponseEntity<ApiResponseDto<BatchDto>> getBatchByName(@PathVariable String batchName) {
        BatchDto batch = batchService.getBatchByName(batchName);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        batch
                )
        );
    }

    @Operation(summary = "Get All Batches", description = "Fetch a list of all available batches")
    @GetMapping("/getAllBatches")
    public ResponseEntity<ApiResponseDto<List<BatchDto>>> getAllBatches() {
        List<BatchDto> batches = batchService.getAllBatches();

        ApiResponseDto<List<BatchDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(batches);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update Batch", description = "Update an existing batch by its name with full details")
    @PutMapping("/update/{batchName}")
    public ResponseEntity<ApiResponseDto<BatchDto>> updateBatch(
            @PathVariable String batchName,
            @RequestBody BatchDto dto) {
        BatchDto updated = batchService.updateBatchByName(batchName, dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    @Operation(summary = "Delete Batch", description = "Delete a batch by its name")
    @DeleteMapping("/delete/{batchName}")
    public ResponseEntity<ApiResponseDto<Void>> deleteBatch(@PathVariable String batchName) {
        batchService.deleteBatchByName(batchName);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_DELETED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        null
                )
        );
    }

    @Operation(summary = "Search Batches", description = "Search batches by optional filters: name, start time, and end time")
    @GetMapping("/search")
    public List<BatchDto> searchBatches(
            @RequestParam(required = false) String batchName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime
    ) {
        return batchService.searchBatches(batchName, startTime, endTime);
    }
}
