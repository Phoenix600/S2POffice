package com.s2p.repository;

import com.s2p.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission,UUID>
{
    Optional<Admission> findByAdmissionDate(LocalDate admissionDate);

    @Query("SELECT FUNCTION('YEAR', a.admissionDate), FUNCTION('MONTH', a.admissionDate), COUNT(a) " +
            "FROM Admission a GROUP BY FUNCTION('YEAR', a.admissionDate), FUNCTION('MONTH', a.admissionDate)")
    List<Object[]> countAdmissionsByMonth();
}
