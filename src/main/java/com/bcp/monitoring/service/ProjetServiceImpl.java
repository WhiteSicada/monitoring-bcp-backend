package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.ProjetConvertor;
import com.bcp.monitoring.dto.api.ListApisDto;
import com.bcp.monitoring.dto.projet.ProjetDto;
import com.bcp.monitoring.dto.projet.ProjetDtoShow;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Projet;
import com.bcp.monitoring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProjetServiceImpl implements ProjetService{

    @Autowired
    public ProjetRepository projetRepository;

    @Autowired
    public ApiRepository apiRepository;

    @Autowired
    public ProjetConvertor projetConvertor;

    @Autowired
    public ResponsableItRepository responsableItRepository;

    @Autowired
    public ResponsableMetierRepository responsableMetierRepository;

    @Autowired
    public EquipeRepository equipeRepository;

    @Transactional
    @Override
    public ProjetDtoShow createProjet(ProjetDto projetDto) {
        Projet projet = new Projet();
        boolean formatingCompleted = projetConvertor.dtoToEntity(projetDto, projet);
        if (formatingCompleted) {
            projetRepository.save(projet);
            return projetConvertor.entityToDto(projet);
        } else {
            return null;
        }
    }

    @Override
    public List<ProjetDtoShow> getAllProjets() {
        List<Projet> projetDtoList = projetRepository.findAllByOrderByIdDesc();
        return projetConvertor.entitysToDtos(projetDtoList);
    }

    @Transactional
    @Override
    public ProjetDtoShow updateProjet(Long id, ProjetDto projetDto) {
        Optional<Projet> projet = projetRepository.findById(id);
        if (projet.isPresent()){
            boolean formatingCompleted = projetConvertor.dtoToEntity(projetDto,projet.get());
            if (formatingCompleted) {
                projetRepository.save(projet.get());
                return projetConvertor.entityToDto(projet.get());
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public String deleteProjet(Long id) {
        Optional<Projet> projet = projetRepository.findById(id);
        if (projet.isPresent()){
            projetRepository.deleteById(projet.get().getId());
            return "Projet with id: " + id + " deleted successfully!";
        }
        return "Projet with id: " + id + " not Found!";
    }

    @Override
    public ProjetDtoShow addApisToProjet(Long id, ListApisDto apis) {
        Optional<Projet> projet = projetRepository.findById(id);
        if (projet.isPresent()){
            for (String api : apis.getApis()){
                Optional<Api> apiTest = apiRepository.findByName(api);
                if (apiTest.isPresent()){
                    projet.get().addAPI(apiTest.get());
                }
                else{
                    return null;
                }
            }
            Projet saveProjet = projetRepository.save(projet.get());
            return projetConvertor.entityToDto(saveProjet);
        }
        return null;
    }

    @Override
    public ProjetDtoShow removeApisFromProjet(Long id, ListApisDto apis) {
        Optional<Projet> projet = projetRepository.findById(id);
        if (projet.isPresent()){
            for (String api : apis.getApis()){
                Optional<Api> apiTest = apiRepository.findByName(api);
                if (apiTest.isPresent()){
                    projet.get().removeAPI(apiTest.get());
                }
                else{
                    return null;
                }
            }
            Projet saveProjet = projetRepository.save(projet.get());
            return projetConvertor.entityToDto(saveProjet);
        }
        return null;
    }
}
