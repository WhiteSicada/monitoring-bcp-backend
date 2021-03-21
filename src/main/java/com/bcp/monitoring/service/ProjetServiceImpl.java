package com.bcp.monitoring.service;

import com.bcp.monitoring.Convertor.ProjetConvertor;
import com.bcp.monitoring.dto.ProjetDto;
import com.bcp.monitoring.model.Projet;
import com.bcp.monitoring.repository.ApiRepository;
import com.bcp.monitoring.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjetServiceImpl implements ProjetService{

    @Autowired
    public ProjetRepository projetRepository;

    @Autowired
    public ApiRepository apiRepository;

    @Autowired
    public ProjetConvertor projetConvertor;

    @Transactional
    @Override
    public ProjetDto createProjet(ProjetDto projetDto) {
        Projet projet = new Projet();
        projetConvertor.dtoToEntity(projetDto, projet);
        Projet saveProjet = projetRepository.save(projet);
        return projetConvertor.entityToDto(saveProjet);
    }

    @Override
    public List<ProjetDto> getAllProjets() {
        return null;
    }

    @Transactional
    @Override
    public ProjetDto updateProjet(Long id, ProjetDto projetDto) {
        return null;
    }

    @Override
    public String deleteProjet(Long id) {
        return null;
    }
}
