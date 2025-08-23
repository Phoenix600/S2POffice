package com.s2p.master.repository;

import com.s2p.master.model.College;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CollegeRepository extends JpaRepository<College, UUID> {

    Optional<College> findByCollegeName(String collegeName);

    void deleteByCollegeName(String collegeName);

    boolean existsByCollegeName(String collegeName);
}
