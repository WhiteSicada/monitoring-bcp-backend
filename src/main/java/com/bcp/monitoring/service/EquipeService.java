package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.EquipeDto;

import java.util.List;

public interface EquipeService {

    EquipeDto createEquipe(EquipeDto equipeDto);

    EquipeDto updateEquipe(Long id, EquipeDto equipeDto);

    String deleteEquipe(Long id);

    List<EquipeDto> getEquipeList();
}
