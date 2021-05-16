package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint,Long > {
    Optional<Endpoint> findByName(String name);
}
