package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Context;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContextRepository extends JpaRepository<Context,Long > {

    Optional<Context> findByName(String name);
}
