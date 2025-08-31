package com.s2p.repository;

import com.s2p.model.CourseFeeInstallmentTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseFeeInstallmentTransactionsRepository extends JpaRepository<CourseFeeInstallmentTransactions,UUID>
{
    List<CourseFeeInstallmentTransactions> findByCourse_CourseName(String courseName);

    List<CourseFeeInstallmentTransactions> findByStudentUsers_Username(String username);
}
