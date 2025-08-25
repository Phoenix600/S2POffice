package com.s2p.master.service;

import com.s2p.master.model.College;

import java.util.List;
import java.util.UUID;

public interface ICollegeService {

    College createCollege(College college);

    List<College> getAllColleges();

    College getCollegeById(UUID id);

    College getCollegeByName(String name);

    College updateCollege(UUID id, College collegeDetails);

    void deleteCollegeById(UUID id);

    void deleteCollegeByName(String name);
}
