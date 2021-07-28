package com.bcp.monitoring.convertor;

import com.bcp.monitoring.dto.projet.ProjetDto;
import com.bcp.monitoring.dto.projet.ProjetDtoShow;
import com.bcp.monitoring.model.Equipe;
import com.bcp.monitoring.model.Projet;
import com.bcp.monitoring.model.ResponsableIt;
import com.bcp.monitoring.model.ResponsableMetier;
import com.bcp.monitoring.repository.ApiRepository;
import com.bcp.monitoring.repository.EquipeRepository;
import com.bcp.monitoring.repository.ResponsableItRepository;
import com.bcp.monitoring.repository.ResponsableMetierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProjetConvertor {
    @Autowired
    public ApiRepository apiRepository;

    @Autowired
    public ResponsableItRepository responsableItRepository;

    @Autowired
    public ResponsableMetierRepository responsableMetierRepository;

    @Autowired
    public EquipeRepository equipeRepository;

    // Convert One Entity project to One project Dto
    public ProjetDtoShow entityToDto(Projet projet){
        // init our projetDto
        ProjetDtoShow projetDtoShow = new ProjetDtoShow();
        // set projetDto infos
        projetDtoShow.setId(projet.getId());
        projetDtoShow.setName(projet.getName());
        projetDtoShow.setResponsableIt(projet.getResponsableIt().getName());
        projetDtoShow.setResponsableMetier(projet.getResponsableMetier().getName());
        projetDtoShow.setEquipe(projet.getEquipe().getName());
        projetDtoShow.setDescription(projet.getDescription());
        projetDtoShow.setListAPIs(projet.getApis());
        // return projetDto
        return projetDtoShow;
    }

    // Convert list of Project Entities to a list of Project DTOs
    public List<ProjetDtoShow> entitysToDtos(List<Projet> projetList){
        return projetList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    // Convert One project Dto project to One Entity
    public boolean dtoToEntity(ProjetDto projetDto, Projet projet){

        // set projet infos

        Optional<ResponsableIt> responsableIt = responsableItRepository.findByName(projetDto.getResponsableIt());
        Optional<ResponsableMetier> responsableMetier = responsableMetierRepository.findByName(projetDto.getResponsableMetier());
        Optional<Equipe> equipe = equipeRepository.findByName(projetDto.getEquipe());
        if (responsableIt.isPresent() && responsableMetier.isPresent() && equipe.isPresent()){
            projet.setName(projetDto.getName());
            projet.setDescription(projetDto.getDescription());
            if (projet.getApis() == null){
                projet.setApis(new HashSet<>());
            }else{
                projet.setApis(projetDto.getListAPIs());
            }
            projet.setResponsableIt(responsableIt.get());
            projet.setResponsableMetier(responsableMetier.get());
            projet.setEquipe(equipe.get());
            return true;
        }
        return false;
    }
}
