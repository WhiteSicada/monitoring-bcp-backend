package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApiRepository extends JpaRepository<Api,Long> {
    Optional<Api> findByName(String name);
    List<Api> findAllByOrderByIdDesc();
}
