package com.s2p.services;

import com.s2p.dto.BatchDto;
import org.apache.coyote.BadRequestException;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface IBatchService
{
    public abstract BatchDto createBatch(BatchDto batchDto) throws BadRequestException;

    public abstract BatchDto getBatchById(UUID batchId);

    public abstract List<BatchDto> getAllBatches();

//    public abstract BatchDto partialUpdateBatchById(UUID batchId);

    public abstract BatchDto updateBatchById(UUID batchId, BatchDto batchDto);

    public abstract BatchDto deleteBatchById(UUID batchId);

    List<BatchDto> searchBatches(String batchName, LocalTime startTime, LocalTime endTime);
}
