package com.s2p.services;

import com.s2p.dto.BatchDto;

import java.util.List;
import java.util.UUID;

public interface IBatchService
{
    public abstract BatchDto createBatch(BatchDto batchDto);

    public abstract BatchDto getBatchById(UUID BatchDto);

    public abstract List<BatchDto> getAllBatches();

    public abstract BatchDto partialUpdateBatchById(UUID BatchDto);

    public abstract BatchDto updateBatchById(UUID BatchDto);

    public abstract BatchDto deleteBatchById(UUID BatchDto);
}
