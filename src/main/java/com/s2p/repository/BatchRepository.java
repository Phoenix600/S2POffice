package com.s2p.repository;

import com.s2p.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BatchRepository extends JpaRepository<Batch, UUID>, JpaSpecificationExecutor<Batch>
{
}
