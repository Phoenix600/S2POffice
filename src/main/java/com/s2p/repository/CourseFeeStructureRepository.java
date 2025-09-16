package com.s2p.repository;

import com.s2p.model.CourseFeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseFeeStructureRepository extends JpaRepository<CourseFeeStructure, UUID>
{
    Optional<CourseFeeStructure> findByStudentUsers_Email(String email);

    Optional<CourseFeeStructure> findByCourse_CourseName(String courseName);

    @Query("""
        SELECT COALESCE(SUM(cf.courseFees), 0)
        FROM CourseFeeStructure cfs
        JOIN cfs.courseFees cf
        JOIN cfs.studentInformation s
        WHERE s.isAdmitted = true
          AND MONTH(s.createdAt) = MONTH(:date)
          AND YEAR(s.createdAt) = YEAR(:date)
    """)
    Double getExpectedFeesForAdmissionsThisMonth(@Param("date") LocalDate date);

    // Collected fees for admitted students in the given month
    @Query("""
        SELECT COALESCE(SUM(cf.amountPaid), 0)
        FROM CourseFeeStructure cfs
        JOIN cfs.courseFees cf
        JOIN cfs.studentInformation s
        WHERE s.isAdmitted = true
          AND MONTH(cf.paymentDate) = MONTH(:date)
          AND YEAR(cf.paymentDate) = YEAR(:date)
    """)
    Double getCollectedFeesThisMonth(@Param("date") LocalDate date);

}
