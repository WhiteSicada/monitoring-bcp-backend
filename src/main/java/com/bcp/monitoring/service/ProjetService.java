package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.api.ListApisDto;
import com.bcp.monitoring.dto.projet.ProjetDto;
import com.bcp.monitoring.dto.projet.ProjetDtoShow;

import java.util.List;

public interface ProjetService {

    ProjetDtoShow createProjet(ProjetDto projetDto);

    List<ProjetDtoShow> getAllProjets();

    ProjetDtoShow updateProjet(Long id, ProjetDto projetDto);

    String deleteProjet(Long id);

    ProjetDtoShow addApisToProjet(Long id, ListApisDto apis);

    ProjetDtoShow removeApisFromProjet(Long id, ListApisDto apis);
}
