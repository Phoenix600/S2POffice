package com.s2p.repository;

import com.s2p.model.AdminUsers;
import com.s2p.model.SuperAdminUsers;
import com.s2p.model.TeacherUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherUsers, UUID>
{
    public abstract Optional<TeacherUsers> findByEmail(String email);

}
