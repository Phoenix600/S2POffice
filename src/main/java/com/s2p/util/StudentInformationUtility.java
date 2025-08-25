package com.s2p.util;

import com.s2p.dto.StudentInformationDto;
import com.s2p.model.StudentInformation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentInformationUtility
{
    public abstract StudentInformation toStudentInformationEntity(StudentInformationDto studentInformationDto);
    public abstract StudentInformationDto toStudentInformationDto(StudentInformation studentInformation);

}
