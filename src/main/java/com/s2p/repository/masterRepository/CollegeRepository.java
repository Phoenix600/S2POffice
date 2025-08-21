package com.s2p.repository.masterRepository;

import com.s2p.master.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CollegeRepository extends JpaRepository<College, UUID> {

}
