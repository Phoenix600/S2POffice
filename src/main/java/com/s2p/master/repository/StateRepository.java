package com.s2p.master.repository;

import com.s2p.master.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StateRepository extends JpaRepository<State, UUID> {
    boolean existsByStateName(String stateName);
}
