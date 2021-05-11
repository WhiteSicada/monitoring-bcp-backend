package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Long> {
    Optional<Equipe> findByName(String name);

}
