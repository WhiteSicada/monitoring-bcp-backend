package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.ResponsableIt;
import com.bcp.monitoring.model.ResponsableMetier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsableMetierRepository extends JpaRepository<ResponsableMetier,Long> {
    Optional<ResponsableMetier> findByName(String name);

}
