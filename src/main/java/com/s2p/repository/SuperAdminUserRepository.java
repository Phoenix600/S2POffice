package com.s2p.repository;

import com.s2p.model.SuperAdminUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SuperAdminUserRepository extends JpaRepository<SuperAdminUsers, UUID>

{
    public abstract Optional<SuperAdminUsers> findByUsername(String username);
}
