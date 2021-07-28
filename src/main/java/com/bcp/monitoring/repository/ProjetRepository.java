package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Long> {
    Optional<Projet>  findByName(String name);
    List<Projet> findAllByOrderByIdDesc();
    Projet findByApis(Api api);
}
