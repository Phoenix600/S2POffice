package com.s2p.repository;

import com.s2p.model.APIRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APIRequestLogRepository extends JpaRepository<APIRequestLog, Long> {
}
