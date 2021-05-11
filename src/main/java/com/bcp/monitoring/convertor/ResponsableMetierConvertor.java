package com.bcp.monitoring.convertor;

import com.bcp.monitoring.dto.ResponsableMetierDto;
import com.bcp.monitoring.model.ResponsableMetier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponsableMetierConvertor {

    public ResponsableMetierDto entityToDto(ResponsableMetier responsableMetier){
        ResponsableMetierDto responsableMetierDto = new ResponsableMetierDto();
        responsableMetierDto.setId(responsableMetier.getId());
        responsableMetierDto.setName(responsableMetier.getName());
        responsableMetierDto.setEmail(responsableMetier.getEmail());
        return responsableMetierDto;
    }

    public List<ResponsableMetierDto> entitiesToDtos(List<ResponsableMetier> responsableMetiers){
        return responsableMetiers.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public void dtoToEntity(ResponsableMetierDto responsableMetierDto,ResponsableMetier responsableMetier){
        responsableMetier.setId(responsableMetierDto.getId());
        responsableMetier.setName(responsableMetierDto.getName());
        responsableMetier.setEmail(responsableMetierDto.getEmail());
    }
}
