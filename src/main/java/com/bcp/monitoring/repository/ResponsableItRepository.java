package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.ResponsableIt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableItRepository extends JpaRepository<ResponsableIt,Long> {
}
