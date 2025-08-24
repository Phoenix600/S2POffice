package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.BatchDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.BatchService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/batch")
public class BatchController
{
    @Autowired
    BatchService batchService;

    // Create Batch
    @PostMapping
    public ResponseEntity<ApiResponseDto<BatchDto>> createBatch(@Valid @RequestBody BatchDto batchDto) throws BadRequestException {
        BatchDto createdBatch = batchService.createBatch(batchDto);

        ApiResponseDto<BatchDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(createdBatch);

        return ResponseEntity.ok(response);
    }

    //  Get Batch by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<BatchDto>> getBatchById(@PathVariable("id") UUID id) {
        BatchDto batch = batchService.getBatchById(id);

        ApiResponseDto<BatchDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(batch);

        return ResponseEntity.ok(response);
    }

    // Get All Batches
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<BatchDto>>> getAllBatches() {
        List<BatchDto> batches = batchService.getAllBatches();

        ApiResponseDto<List<BatchDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(batches);

        return ResponseEntity.ok(response);
    }

    // Full Update Batch
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<BatchDto>> updateBatch(@PathVariable("id") UUID id,
                                                                @Valid @RequestBody BatchDto batchDto) {
        BatchDto updatedBatch = batchService.updateBatchById(id, batchDto);

        ApiResponseDto<BatchDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
        response.setData(updatedBatch);

        return ResponseEntity.ok(response);
    }

    // Delete Batch
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteBatch(@PathVariable("id") UUID id) {
        batchService.deleteBatchById(id);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("Batch deleted successfully");

        return ResponseEntity.ok(response);
    }
}

