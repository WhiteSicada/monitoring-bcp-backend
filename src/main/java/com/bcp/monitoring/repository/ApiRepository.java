package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends JpaRepository<Api,Long> {
}
