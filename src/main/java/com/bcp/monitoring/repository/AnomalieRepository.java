package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Anomalie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnomalieRepository extends JpaRepository<Anomalie,Long> {
}
