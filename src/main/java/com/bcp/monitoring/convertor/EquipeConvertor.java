package com.bcp.monitoring.convertor;

import com.bcp.monitoring.dto.EquipeDto;
import com.bcp.monitoring.model.Equipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipeConvertor {

    public EquipeDto entityToDto(Equipe equipe){
        EquipeDto equipeDto = new EquipeDto();
        equipeDto.setId(equipe.getId());
        equipeDto.setName(equipe.getName());
        return equipeDto;
    }
    public List<EquipeDto> entitiesToDtos(List<Equipe> equipeList){
        return equipeList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public void dtoToEntity(EquipeDto equipeDto, Equipe equipe){
        equipe.setId(equipeDto.getId());
        equipe.setName(equipeDto.getName());
    }
}
