package com.s2p.services.impl;

import com.s2p.dto.BatchDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Batch;
import com.s2p.repository.BatchRepository;
import com.s2p.services.IBatchService;
import com.s2p.util.BatchUtility;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BatchService implements IBatchService
{
    @Autowired
    BatchRepository batchRepository;

    @Override
    public BatchDto createBatch(BatchDto batchDto) throws BadRequestException {
        List<Batch> allBatches = batchRepository.findAll();

        for (Batch existingBatch : allBatches) {
            if (existingBatch.getBatchName().equalsIgnoreCase(batchDto.getBatchName())) {
                throw new BadRequestException("Batch already exists with name: " + batchDto.getBatchName());
            }
        }

        Batch batch = BatchUtility.toBatchEntity(batchDto);
        Batch savedBatch = batchRepository.save(batch);
        return BatchUtility.toBatchDto(savedBatch);
    }

    @Override
    public BatchDto getBatchById(UUID batchId) {
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if (optionalBatch.isEmpty()) {
            throw new ResourceNotFoundException("Batch", "id", batchId.toString());
        }

        return BatchUtility.toBatchDto(optionalBatch.get());
    }

    @Override
    public List<BatchDto> getAllBatches() {
        List<Batch> batches = batchRepository.findAll();
        List<BatchDto> result = new ArrayList<>();

        for (Batch batch : batches) {
            result.add(BatchUtility.toBatchDto(batch));
        }

        return result;
    }



    @Override
    public BatchDto updateBatchById(UUID batchId, BatchDto batchDto) {
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if (optionalBatch.isEmpty()) {
            throw new ResourceNotFoundException("Batch", "id", batchId.toString());
        }

        Batch existingBatch = optionalBatch.get();
        existingBatch.setBatchName(batchDto.getBatchName());
        existingBatch.setStartTime(batchDto.getStartTime());
        existingBatch.setEndTime(batchDto.getEndTime());

        Batch updatedBatch = batchRepository.save(existingBatch);
        return BatchUtility.toBatchDto(updatedBatch);
    }

    @Override
    public BatchDto deleteBatchById(UUID batchId) {
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if (optionalBatch.isEmpty()) {
            throw new ResourceNotFoundException("Batch", "id", batchId.toString());
        }

        Batch batch = optionalBatch.get();
        batchRepository.delete(batch);

        return BatchUtility.toBatchDto(batch);
    }
}
