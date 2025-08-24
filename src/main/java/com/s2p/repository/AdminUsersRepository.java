package com.s2p.repository;

import com.s2p.model.AdminUsers;
import com.s2p.model.SuperAdminUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminUsersRepository extends JpaRepository<AdminUsers, UUID>
{
   public abstract Optional<AdminUsers> findByEmail(String email);
}
