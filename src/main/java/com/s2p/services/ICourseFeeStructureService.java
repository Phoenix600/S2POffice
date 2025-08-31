package com.s2p.services;

import com.s2p.dto.CourseFeeStructureDto;

import java.util.Set;
import java.util.UUID;

public interface ICourseFeeStructureService
{
    public abstract CourseFeeStructureDto createCourseFeeStructure(CourseFeeStructureDto courseFeeStructureDto);

    public abstract CourseFeeStructureDto getCourseFeeStructureById(UUID courseFeeStructureId);

    public abstract Set<CourseFeeStructureDto> getAllCourseFeeStructures();

    public abstract CourseFeeStructureDto partialUpdateCourseFeeStructureById(UUID courseFeeStructureI, CourseFeeStructureDto courseFeeStructureDto);

    CourseFeeStructureDto updateCourseFeeStructureById(UUID courseFeeStructureId, CourseFeeStructureDto courseFeeStructureDto);

    public abstract CourseFeeStructureDto deleteCourseFeeStructureById(UUID courseFeeStructureId);
}
