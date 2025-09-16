package com.s2p.repository;

import com.s2p.model.StudentInformation;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface StudentInformationRepository extends JpaRepository<StudentInformation, UUID>,
        JpaSpecificationExecutor<StudentInformation> {

    Optional<StudentInformation> findByEmail(String email);
}
