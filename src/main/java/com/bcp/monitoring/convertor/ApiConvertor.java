package com.bcp.monitoring.convertor;

import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.api.ApiDtoShow;
import com.bcp.monitoring.model.Anomalie;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Endpoint;
import com.bcp.monitoring.repository.EndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiConvertor {

    @Autowired
    public EndpointRepository endpointRepository;

    @Autowired
    public EndpointConvertor endpointConvertor;

    public ApiDtoShow entityToDto(Api api){
        ApiDtoShow apiDtoShow = new ApiDtoShow();
        apiDtoShow.setId(api.getId());
        apiDtoShow.setName(api.getName());
        apiDtoShow.setDescription(api.getDescription());
        apiDtoShow.setIp(api.getIp());
        apiDtoShow.setPort(api.getPort());
        apiDtoShow.setStatus(api.isStatus());
        apiDtoShow.setDb(api.isDb());
        apiDtoShow.setDiskspace(api.isDiskspace());
        apiDtoShow.setPing(api.isPing());
        apiDtoShow.setEndpoints(api.getEndpoints());
        apiDtoShow.setAnomalies(api.getAnomalies());
        return apiDtoShow;
    }

    public List<ApiDtoShow> entitiesToDtos(List<Api> apiList){
        return apiList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public boolean dtoToEntity(ApiDto apiDto, Api api){
        api.setId(apiDto.getId());
        api.setName(apiDto.getName());
        api.setDescription(apiDto.getDescription());
        api.setIp(apiDto.getIp());
        api.setPort(apiDto.getPort());
        api.setAnomalies(new ArrayList<Anomalie>());
        if(null == api.getEndpoints()){
            api.setEndpoints(new ArrayList<>());
        }
        apiDto.getEndpointList().stream().forEach(endpoint -> {
            Endpoint endpoint1 = endpointRepository.findByName(endpoint.getName());
            if (null == endpoint1) {
                endpoint1 = new Endpoint();
            }
            endpointConvertor.dtoToEntity(endpoint,endpoint1);
            api.addEndpoint(endpoint1);
        });
        return true;
    }

}
