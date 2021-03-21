package com.bcp.monitoring.Convertor;

import com.bcp.monitoring.dto.ProjetDto;
import com.bcp.monitoring.model.Equipe;
import com.bcp.monitoring.model.Projet;
import com.bcp.monitoring.model.ResponsableIt;
import com.bcp.monitoring.model.ResponsableMetier;
import com.bcp.monitoring.repository.ApiRepository;
import com.bcp.monitoring.repository.ResponsableItRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjetConvertor {
    @Autowired
    public ApiRepository apiRepository;

    @Autowired
    public ResponsableItRepository responsableItRepository;

    // Convert One Entity project to One project Dto
    public ProjetDto entityToDto(Projet projet){
        // init our projetDto
        ProjetDto projetDto = new ProjetDto();
        // set projetDto infos
        projetDto.setId(projet.getId());
        projetDto.setName(projet.getName());
        projetDto.setResponsableIt(projet.getResponsableIt());
        projetDto.setResponsableMetier(projet.getResponsableMetier());
        projetDto.setEquipe(projet.getEquipe());
        projetDto.setDescription(projet.getDescription());
        projetDto.setListAPIs(projet.getListAPIs());
        // return projetDto
        return projetDto;
    }

    // Convert list of Project Entities to a list of Project DTOs
    public List<ProjetDto> entitysToDtos(List<Projet> projetList){
        return projetList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    // Convert One project Dto project to One Entity
    public void dtoToEntity(ProjetDto projetDto, Projet projet){

        // set projet infos

        projet.setName(projetDto.getName());
        projet.setDescription(projetDto.getDescription());

        projet.setResponsableIt(projetDto.getResponsableIt());
        projet.setResponsableMetier(projetDto.getResponsableMetier());
        projet.setEquipe(projetDto.getEquipe());
        if (projet.getListAPIs() == null){
            projet.setListAPIs(new HashSet<>());
        }else{
            projet.setListAPIs(projetDto.getListAPIs());
        }

    }
}
