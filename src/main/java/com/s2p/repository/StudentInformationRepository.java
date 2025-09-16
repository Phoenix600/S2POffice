package com.s2p.repository;

import com.s2p.model.StudentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentInformationRepository extends JpaRepository<StudentInformation, UUID>
{
    Optional<StudentInformation> findByEmail(String email);

    @Query("SELECT COUNT(s) FROM StudentInformation s " +
            "WHERE s.isAdmitted = true " +
            "AND MONTH(s.createdAt) = MONTH(:date) " +
            "AND YEAR(s.createdAt) = YEAR(:date)")
    long countAdmissionsThisMonth(LocalDate date);

    @Query("SELECT COALESCE(SUM(cf.courseFees), 0) " +
                  "FROM StudentInformation s " +
                  "JOIN s.courseFeeStructure cfs " +
                  "JOIN cfs.courseFees cf " +
                  "WHERE s.isAdmitted = true " +
                  "AND MONTH(s.createdAt) = MONTH(:date) " +
                  "AND YEAR(s.createdAt) = YEAR(:date)")
    Double getExpectedFeesForAdmissionsThisMonth(@Param("date") LocalDate date);



}
