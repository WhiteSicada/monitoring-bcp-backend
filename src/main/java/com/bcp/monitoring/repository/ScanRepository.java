package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Anomalie;
import com.bcp.monitoring.model.Scan;
import com.bcp.monitoring.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScanRepository extends JpaRepository<Scan,Long> {
    List<Scan> findAllByTest(Test test);
}
