package com.s2p.util;

import com.s2p.dto.BatchDto;
import com.s2p.model.Batch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class BatchUtility
{
    public final static Batch toBatchEntity(BatchDto batchDto)
    {
        Batch batch = new Batch();

        batch.setBatchId(batchDto.getBatchId());
        batch.setBatchName(batchDto.getBatchName());
        batch.setStartTime(batchDto.getStartTime());
        batch.setEndTime(batchDto.getEndTime());

        return batch;
    }

    public final static BatchDto toBatchDto(Batch batch)
    {
        BatchDto batchDto = new BatchDto();

        batchDto.setBatchId(batch.getBatchId());
        batchDto.setBatchName(batch.getBatchName());
        batchDto.setStartTime(batch.getStartTime());
        batchDto.setEndTime(batch.getEndTime());

        return batchDto;
    }
}
