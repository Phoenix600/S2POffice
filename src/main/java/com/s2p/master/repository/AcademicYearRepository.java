package com.s2p.master.repository;

import com.s2p.master.model.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, UUID> {
    AcademicYear findByAcademicYear(Integer academicYear);
}
