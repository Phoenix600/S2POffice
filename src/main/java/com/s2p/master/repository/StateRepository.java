package com.s2p.master.repository;

import com.s2p.master.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateRepository extends JpaRepository<State, UUID> {
    boolean existsByStateName(String stateName);
}
