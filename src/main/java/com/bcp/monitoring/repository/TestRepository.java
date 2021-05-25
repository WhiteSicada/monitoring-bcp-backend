package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
