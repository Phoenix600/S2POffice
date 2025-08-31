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

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/batch")
public class BatchController
{
    @Autowired
    BatchService batchService;

    // Create Batch
    @PostMapping("/create-batch")
    public ResponseEntity<ApiResponseDto<BatchDto>> createBatch(@Valid @RequestBody BatchDto batchDto) throws BadRequestException
    {
        BatchDto createdBatch = batchService.createBatch(batchDto);

        ApiResponseDto<BatchDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(createdBatch);

        return ResponseEntity.ok(response);
    }

    //  Get Batch by ID
    @GetMapping("/{batchName}")
    public ResponseEntity<ApiResponseDto<BatchDto>> getBatchByName(@PathVariable String batchName)
    {
        BatchDto batch = batchService.getBatchByName(batchName);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        batch
                )
        );
    }


    // Get All Batches
    @GetMapping("/getAllBatches")
    public ResponseEntity<ApiResponseDto<List<BatchDto>>> getAllBatches()
    {
        List<BatchDto> batches = batchService.getAllBatches();

        ApiResponseDto<List<BatchDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(batches);

        return ResponseEntity.ok(response);
    }

    // Full Update Batch
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto<BatchDto>> updateBatch(
            @PathVariable String batchName,
            @RequestBody BatchDto dto)
    {
        BatchDto updated = batchService.updateBatchByName(batchName, dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    // Delete Batch
    @DeleteMapping("/delete/{batchName}")
    public ResponseEntity<ApiResponseDto<Void>> deleteBatch(@PathVariable String batchName)
    {
        batchService.deleteBatchByName(batchName);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_DELETED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        null
                )
        );
    }


    @GetMapping("/search")
    public List<BatchDto> searchBatches(
            @RequestParam(required = false) String batchName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime
    )
    {
        return batchService.searchBatches(batchName, startTime, endTime);
    }
}

