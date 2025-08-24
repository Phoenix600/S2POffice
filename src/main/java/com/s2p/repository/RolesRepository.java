package com.s2p.repository;

import com.s2p.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<Roles,UUID>
{
    Roles findByRolesName(String adminUsers);
}
