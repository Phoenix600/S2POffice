package com.s2p.services;

import com.s2p.dto.BatchDto;
import com.s2p.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BatchService implements IBatchService
{
    @Autowired
    BatchRepository batchRepository;

    @Override
    public BatchDto createBatch(BatchDto batchDto) {
        return null;
    }

    @Override
    public BatchDto getBatchById(UUID BatchDto) {
        return null;
    }

    @Override
    public List<BatchDto> getAllBatches() {
        return List.of();
    }

    @Override
    public BatchDto partialUpdateBatchById(UUID BatchDto) {
        return null;
    }

    @Override
    public BatchDto updateBatchById(UUID BatchDto) {
        return null;
    }

    @Override
    public BatchDto deleteBatchById(UUID BatchDto) {
        return null;
    }
}
