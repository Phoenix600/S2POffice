package com.s2p.services;

import com.s2p.dto.BatchDto;
import org.apache.coyote.BadRequestException;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface IBatchService
{
    public abstract BatchDto createBatch(BatchDto batchDto) throws BadRequestException;

    public abstract BatchDto getBatchByName(String batchName);

    public abstract List<BatchDto> getAllBatches();

    public abstract BatchDto updateBatchByName(String batchName, BatchDto batchDto);

    public abstract void deleteBatchByName(String batchName);

    List<BatchDto> searchBatches(String batchName, LocalTime startTime, LocalTime endTime);
}
