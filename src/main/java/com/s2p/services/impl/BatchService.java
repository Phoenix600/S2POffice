package com.s2p.services.impl;

import com.s2p.dto.BatchDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Batch;
import com.s2p.repository.BatchRepository;
import com.s2p.repository.specifications.BatchSpecification;
import com.s2p.services.IBatchService;
import com.s2p.util.BatchUtility;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BatchService implements IBatchService
{
    @Autowired
    BatchRepository batchRepository;

    @Autowired
    BatchUtility batchUtility;

    @Override
    public BatchDto createBatch(BatchDto batchDto) throws BadRequestException {
        List<Batch> allBatches = batchRepository.findAll();

        for (Batch existingBatch : allBatches) {
            if (existingBatch.getBatchName().equalsIgnoreCase(batchDto.getBatchName())) {
                throw new BadRequestException("Batch already exists with name: " + batchDto.getBatchName());
            }
        }

        Batch batch = batchUtility.toBatchEntity(batchDto);
        Batch savedBatch = batchRepository.save(batch);
        return batchUtility.toBatchDto(savedBatch);
    }

    @Override
    public BatchDto getBatchByName(String batchName) {
        Optional<Batch> optionalBatch = batchRepository.findByBatchName(batchName);

        if (optionalBatch.isEmpty()) {
            throw new ResourceNotFoundException("Batch not found with name: " + batchName);
        }

        return batchUtility.toBatchDto(optionalBatch.get());
    }


    @Override
    public List<BatchDto> getAllBatches() {
        List<Batch> batches = batchRepository.findAll();
        List<BatchDto> result = new ArrayList<>();

        for (Batch batch : batches) {
            result.add(batchUtility.toBatchDto(batch));
        }

        return result;
    }

    @Override
    public BatchDto updateBatchByName(String batchName, BatchDto batchDto) {
        Optional<Batch> optionalBatch = batchRepository.findByBatchName(batchName);

        if (optionalBatch.isEmpty()) {
            throw new ResourceNotFoundException("Batch not found with name: " + batchName);
        }

        Batch batch = optionalBatch.get();
        batch.setBatchName(batchDto.getBatchName());
        batch.setStartTime(batchDto.getStartTime());
        batch.setEndTime(batchDto.getEndTime());

        Batch updated = batchRepository.save(batch);
        return batchUtility.toBatchDto(updated);
    }

    @Override
    public void deleteBatchByName(String batchName) {
        Optional<Batch> optionalBatch = batchRepository.findByBatchName(batchName);

        if (optionalBatch.isEmpty()) {
            throw new ResourceNotFoundException("Batch not found with name: " + batchName);
        }

        batchRepository.delete(optionalBatch.get());

    }

    @Override
    public List<BatchDto> searchBatches(String batchName, LocalTime startTime, LocalTime endTime) {
        Specification<Batch> spec = Specification.anyOf(
                BatchSpecification.hasBatchName(batchName))
                .or(BatchSpecification.hasStartTime(startTime))
                .or(BatchSpecification.hasEndTime(endTime));

        return batchRepository.findAll(spec)
                .stream()
                .map(batchUtility::toBatchDto)
                .collect(Collectors.toList());
    }

}
