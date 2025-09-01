package com.s2p.services;

import com.s2p.dto.CourseFeeStructureDto;

import java.util.Set;
import java.util.UUID;

public interface ICourseFeeStructureService
{
    public abstract CourseFeeStructureDto createCourseFeeStructure(CourseFeeStructureDto courseFeeStructureDto);

    public abstract CourseFeeStructureDto getFeeStructureByCourseName(String courseName);

    public abstract CourseFeeStructureDto getFeeStructureByStudentEmail(String email);

    public abstract Set<CourseFeeStructureDto> getAllCourseFeeStructures();

    public abstract CourseFeeStructureDto updateFeeStructureByStudentEmail(String email, CourseFeeStructureDto dto);

    public abstract  void deleteFeeStructureByStudentEmail(String email);
}
