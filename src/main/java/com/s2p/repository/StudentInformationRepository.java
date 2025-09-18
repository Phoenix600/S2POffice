package com.s2p.repository;

import com.s2p.model.StudentInformation;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentInformationRepository extends JpaRepository<StudentInformation, UUID>,
        JpaSpecificationExecutor<StudentInformation> {

    Optional<StudentInformation> findByEmail(String email);

    @Query("SELECT FUNCTION('YEAR', s.createdDate), FUNCTION('MONTH', s.createdDate), COUNT(s) " +
            "FROM StudentInformation s GROUP BY FUNCTION('YEAR', s.createdDate), FUNCTION('MONTH', s.createdDate)")
    List<Object[]> countStudentsByMonth();

}
