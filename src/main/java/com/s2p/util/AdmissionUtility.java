package com.s2p.util;

import com.s2p.dto.AdmissionDto;
import com.s2p.model.Admission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AdmissionUtility
{
    public abstract Admission toAdmissionEntity(AdmissionDto admissionDto);
    public abstract AdmissionDto toAdmissionDto(Admission admission);

}
