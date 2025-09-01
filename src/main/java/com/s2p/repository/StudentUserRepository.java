package com.s2p.repository;

import com.s2p.model.CourseFeeInstallmentTransactions;
import com.s2p.model.StudentUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentUserRepository extends JpaRepository<StudentUsers, UUID>
{
    public abstract Optional<StudentUsers> findByEmail(String email);

    public abstract Optional<StudentUsers> findByUsername(String username);
}
