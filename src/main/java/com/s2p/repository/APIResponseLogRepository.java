package com.s2p.repository;

import com.s2p.model.ApiResponseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APIResponseLogRepository extends JpaRepository<ApiResponseLog, Long> {
}
