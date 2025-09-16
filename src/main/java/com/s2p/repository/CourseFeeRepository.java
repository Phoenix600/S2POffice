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


    @Query("SELECT COALESCE(SUM(cfs.courseFees.amountPaid), 0) " +
            "FROM CourseFeeStructure cfs " +
            "JOIN cfs.courseFees cf " +
            "WHERE MONTH(cf.paymentDate) = MONTH(:date) " +
            "AND YEAR(cf.paymentDate) = YEAR(:date)")
    Double getCollectedFeesThisMonth(LocalDate date);


}
