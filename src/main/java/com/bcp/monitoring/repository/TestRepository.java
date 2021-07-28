package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findAllByOrderByIdDesc();
}
