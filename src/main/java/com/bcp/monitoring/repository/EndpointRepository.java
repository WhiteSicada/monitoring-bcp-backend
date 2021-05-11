package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint,Long > {
    public Endpoint findByName(String name);
}
