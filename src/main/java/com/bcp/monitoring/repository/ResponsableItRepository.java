package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.ResponsableIt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsableItRepository extends JpaRepository<ResponsableIt,Long> {
    Optional<ResponsableIt> findByName(String name);
}
