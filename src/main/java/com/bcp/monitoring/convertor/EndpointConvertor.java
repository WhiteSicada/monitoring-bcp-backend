package com.bcp.monitoring.convertor;

import com.bcp.monitoring.dto.endpoint.EndpointDto;
import com.bcp.monitoring.model.Endpoint;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EndpointConvertor {

    public EndpointDto entityToDoto(Endpoint endpoint){
        EndpointDto endpointDto = new EndpointDto();
        endpointDto.setId(endpoint.getId());
        endpointDto.setName(endpoint.getName());
        endpointDto.setUrl(endpoint.getUrl());
        endpointDto.setMethod(endpoint.getMethod());
        endpointDto.setData(endpoint.getData());
        return endpointDto;
    }

    public List<EndpointDto> entitiesToDotos(List<Endpoint> endpointList){
        return endpointList.stream().map(this::entityToDoto).collect(Collectors.toList());
    }

    public void dtoToEntity(EndpointDto endpointDto, Endpoint endpoint){
        endpoint.setId(endpointDto.getId());
        endpoint.setName(endpointDto.getName());
        endpoint.setUrl(endpointDto.getUrl());
        endpoint.setMethod(endpointDto.getMethod());
        endpoint.setData((endpointDto.getData()));
    }
}
