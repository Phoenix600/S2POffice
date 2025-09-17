package com.s2p.repository;

import com.s2p.master.model.AcademicYear;
import com.s2p.model.CourseFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CourseFeeRepository extends JpaRepository<CourseFees,UUID>
{
    List<CourseFees> findByCourse_CourseName(String courseName);

    List<CourseFees> findByAcademicYear(AcademicYear academicYear);


    // Expected fees for admissions in a given month
    @Query("SELECT COALESCE(SUM(cf.amountExpected), 0) " +
            "FROM CourseFees cf " +
            "JOIN cf.studentInformation s " +
            "WHERE s.isAdmitted = true " +
            "AND FUNCTION('MONTH', s.createdAt) = FUNCTION('MONTH', :date) " +
            "AND FUNCTION('YEAR', s.createdAt) = FUNCTION('YEAR', :date)")
    Double getExpectedFeesForMonth(@Param("date") LocalDate date);

    // Actual fees collected in a given month
    @Query("SELECT COALESCE(SUM(cf.amountPaid), 0) " +
            "FROM CourseFees cf " +
            "JOIN cf.studentInformation s " +
            "WHERE s.isAdmitted = true " +
            "AND FUNCTION('MONTH', cf.paymentDate) = FUNCTION('MONTH', :date) " +
            "AND FUNCTION('YEAR', cf.paymentDate) = FUNCTION('YEAR', :date)")
    Double getCollectedFeesForMonth(@Param("date") LocalDate date);

    // Monthly expected fees
    @Query("SELECT FUNCTION('YEAR', cf.paymentDate), FUNCTION('MONTH', cf.paymentDate), COALESCE(SUM(cf.amountExpected), 0) " +
            "FROM CourseFees cf " +
            "GROUP BY FUNCTION('YEAR', cf.paymentDate), FUNCTION('MONTH', cf.paymentDate) " +
            "ORDER BY FUNCTION('YEAR', cf.paymentDate), FUNCTION('MONTH', cf.paymentDate)")
    List<Object[]> countExpectedFeesByMonth();

    // Monthly actual fees collected
    @Query("SELECT FUNCTION('YEAR', cf.paymentDate), FUNCTION('MONTH', cf.paymentDate), COALESCE(SUM(cf.amountPaid), 0) " +
            "FROM CourseFees cf " +
            "GROUP BY FUNCTION('YEAR', cf.paymentDate), FUNCTION('MONTH', cf.paymentDate) " +
            "ORDER BY FUNCTION('YEAR', cf.paymentDate), FUNCTION('MONTH', cf.paymentDate)")
    List<Object[]> countActualFeesByMonth();
}


