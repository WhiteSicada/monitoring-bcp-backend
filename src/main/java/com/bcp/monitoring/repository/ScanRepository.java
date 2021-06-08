package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanRepository extends JpaRepository<Scan,Long> {
}
