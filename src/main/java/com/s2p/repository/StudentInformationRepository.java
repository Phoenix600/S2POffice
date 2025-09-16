package com.s2p.repository;

import com.s2p.model.StudentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentInformationRepository extends JpaRepository<StudentInformation, UUID> {
    Optional<StudentInformation> findByEmail(String email);
}
