package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.ProjetDto;

import java.util.List;

public interface ProjetService {

    ProjetDto createProjet(ProjetDto projetDto);

    List<ProjetDto> getAllProjets();

//    ProjetDto getProjet(Long id);

    ProjetDto updateProjet(Long id, ProjetDto projetDto);

    String deleteProjet(Long id);
}
