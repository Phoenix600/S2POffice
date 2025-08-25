package com.s2p.util;

import com.s2p.dto.BatchDto;
import com.s2p.model.Batch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BatchUtility
{
    public abstract Batch toBatchEntity(BatchDto batchDto);
    public abstract BatchDto toBatchDto(Batch batch);

}
