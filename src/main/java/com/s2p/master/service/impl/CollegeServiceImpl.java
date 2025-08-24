package com.s2p.master.service.impl;

import com.s2p.master.model.College;
import com.s2p.master.repository.CollegeRepository;
import com.s2p.master.service.ICollegeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollegeServiceImpl implements ICollegeService {

    private final CollegeRepository collegeRepository;

    @Override
    public College createCollege(College college) {
        if (collegeRepository.existsByCollegeName(college.getCollegeName())) {
            throw new IllegalArgumentException("College with the same name already exists");
        }
        return collegeRepository.save(college);
    }

    @Override
    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    @Override
    public College getCollegeById(UUID id) {
        Optional<College> optionalCollege = collegeRepository.findById(id);
        if (optionalCollege.isPresent()) {
            return optionalCollege.get();
        }
        throw new EntityNotFoundException("College not found with id: " + id);
    }

    @Override
    public College getCollegeByName(String name) {
        Optional<College> optionalCollege = collegeRepository.findByCollegeName(name);
        if (optionalCollege.isPresent()) {
            return optionalCollege.get();
        }
        throw new EntityNotFoundException("College not found with name: " + name);
    }

    @Override
    public College updateCollege(UUID id, College collegeDetails) {
        College existingCollege = getCollegeById(id);
        existingCollege.setCollegeName(collegeDetails.getCollegeName());
        existingCollege.setDepartmentSet(collegeDetails.getDepartmentSet());
        return collegeRepository.save(existingCollege);
    }

    @Override
    public void deleteCollegeById(UUID id) {
        if (!collegeRepository.existsById(id)) {
            throw new EntityNotFoundException("College not found with id: " + id);
        }
        collegeRepository.deleteById(id);
    }

    @Override
    public void deleteCollegeByName(String name) {
        if (!collegeRepository.existsByCollegeName(name)) {
            throw new EntityNotFoundException("College not found with name: " + name);
        }
        collegeRepository.deleteByCollegeName(name);
    }
}
