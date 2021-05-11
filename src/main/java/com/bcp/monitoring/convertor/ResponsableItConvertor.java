package com.bcp.monitoring.convertor;

import com.bcp.monitoring.dto.ResponsableItDto;
import com.bcp.monitoring.model.ResponsableIt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponsableItConvertor {

    public ResponsableItDto entityToDto(ResponsableIt responsableIt){
        ResponsableItDto responsableItDto = new ResponsableItDto();
        responsableItDto.setId(responsableIt.getId());
        responsableItDto.setName(responsableIt.getName());
        responsableItDto.setEmail(responsableIt.getEmail());
        return responsableItDto;
    }

    public List<ResponsableItDto> entitiesToDtos(List<ResponsableIt> responsableIts){
        return responsableIts.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public void dtoToEntity(ResponsableItDto responsableItDto,ResponsableIt responsableIt){
        responsableIt.setId(responsableItDto.getId());
        responsableIt.setName(responsableItDto.getName());
        responsableIt.setEmail(responsableItDto.getEmail());
    }
}
