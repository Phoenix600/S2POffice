package com.s2p.repository;

import com.s2p.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission,UUID>
{
}
