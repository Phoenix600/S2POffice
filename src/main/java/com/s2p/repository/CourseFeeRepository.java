package com.s2p.repository;

import com.s2p.master.model.AcademicYear;
import com.s2p.model.CourseFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseFeeRepository extends JpaRepository<CourseFees,UUID>
{
    List<CourseFees> findByCourse_CourseName(String courseName);

    List<CourseFees> findByAcademicYear(AcademicYear academicYear);
}
