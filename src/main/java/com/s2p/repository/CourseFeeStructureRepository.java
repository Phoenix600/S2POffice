package com.s2p.repository;

import com.s2p.model.CourseFeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseFeeStructureRepository extends JpaRepository<CourseFeeStructure, UUID>
{
    Optional<CourseFeeStructure> findByStudentUsers_Email(String email);

    Optional<CourseFeeStructure> findByCourse_CourseName(String courseName);
}
