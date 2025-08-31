package com.s2p.services.impl;

import com.s2p.dto.CourseFeeStructureDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.CourseFeeStructure;
import com.s2p.repository.CourseFeeStructureRepository;
import com.s2p.services.ICourseFeeStructureService;
import com.s2p.util.CourseFeesStructureUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseFeeStructureService implements ICourseFeeStructureService
{
    @Autowired
    CourseFeeStructureRepository courseFeeStructureRepository;

    @Autowired
    CourseFeesStructureUtility courseFeesStructureUtility;

    @Override
    public CourseFeeStructureDto createCourseFeeStructure(CourseFeeStructureDto courseFeeStructureDto) {
        CourseFeeStructure entity = courseFeesStructureUtility.toCourseFeeStructureEntity(courseFeeStructureDto);
        CourseFeeStructure saved = courseFeeStructureRepository.save(entity);
        return courseFeesStructureUtility.toCourseFeeStructureDto(saved);
    }

    @Override
    public CourseFeeStructureDto getCourseFeeStructureById(UUID courseFeeStructureId) {
        Optional<CourseFeeStructure> optional = courseFeeStructureRepository.findById(courseFeeStructureId);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("CourseFeeStructure", "id", courseFeeStructureId.toString());
        }

        return courseFeesStructureUtility.toCourseFeeStructureDto(optional.get());
    }

    @Override
    public Set<CourseFeeStructureDto> getAllCourseFeeStructures() {
        List<CourseFeeStructure> structures = courseFeeStructureRepository.findAll();
        Set<CourseFeeStructureDto> result = new HashSet<>();

        for (CourseFeeStructure structure : structures) {
            result.add(courseFeesStructureUtility.toCourseFeeStructureDto(structure));
        }
        return result;
    }

    @Override
    public CourseFeeStructureDto partialUpdateCourseFeeStructureById(UUID courseFeeStructureI, CourseFeeStructureDto courseFeeStructureDto) {
        return null;
    }

    @Override
    public CourseFeeStructureDto updateCourseFeeStructureById(UUID courseFeeStructureId, CourseFeeStructureDto courseFeeStructureDto)
    {
        Optional<CourseFeeStructure> optional = courseFeeStructureRepository.findById(courseFeeStructureId);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("CourseFeeStructure", "id", courseFeeStructureId.toString());
        }

        CourseFeeStructure existing = optional.get();
//        existing.setAmount(courseFeeStructureDto.getAmount());

        CourseFeeStructure updated = courseFeeStructureRepository.save(existing);
        return courseFeesStructureUtility.toCourseFeeStructureDto(updated);
    }

    @Override
    public CourseFeeStructureDto deleteCourseFeeStructureById(UUID courseFeeStructureId) {
        Optional<CourseFeeStructure> optional = courseFeeStructureRepository.findById(courseFeeStructureId);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("CourseFeeStructure", "id", courseFeeStructureId.toString());
        }

        CourseFeeStructure entity = optional.get();
        courseFeeStructureRepository.delete(entity);
        return courseFeesStructureUtility.toCourseFeeStructureDto(entity);
    }
}
