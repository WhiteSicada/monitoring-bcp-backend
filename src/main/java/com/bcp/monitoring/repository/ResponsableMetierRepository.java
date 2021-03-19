package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.ResponsableMetier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableMetierRepository extends JpaRepository<ResponsableMetier,Long> {
}
