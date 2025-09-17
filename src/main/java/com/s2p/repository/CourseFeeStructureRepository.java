package com.s2p.repository;

import com.s2p.model.CourseFeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseFeeStructureRepository extends JpaRepository<CourseFeeStructure, UUID>
{
    Optional<CourseFeeStructure> findByStudentUsers_Email(String email);

    Optional<CourseFeeStructure> findByCourse_CourseName(String courseName);

    @Query("SELECT YEAR(cfs.createdAt), MONTH(cfs.createdAt), SUM(cfs.downPayment + cfs.remainingAmount) " +
            "FROM CourseFeeStructure cfs " +
            "GROUP BY YEAR(cfs.createdAt), MONTH(cfs.createdAt)")
    List<Object[]> findMonthlyCollectedFees();



}
