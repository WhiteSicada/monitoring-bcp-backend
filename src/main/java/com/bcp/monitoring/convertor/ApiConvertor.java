package com.bcp.monitoring.convertor;

import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.repository.AnomalieRepository;
import com.bcp.monitoring.repository.EndpointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiConvertor {

    Logger logger = LoggerFactory.getLogger(ApiConvertor.class);

    @Autowired
    public EndpointRepository endpointRepository;

    @Autowired
    public EndpointConvertor endpointConvertor;

    @Autowired
    public AnomalieRepository anomalieRepository;

    public ApiDto entityToDto(Api api){
        ApiDto apiDto = new ApiDto();
        apiDto.setId(api.getId());
        apiDto.setName(api.getName());
        apiDto.setDescription(api.getDescription());
        apiDto.setIp(api.getIp());
        apiDto.setPort(api.getPort());
        apiDto.setStatus(api.isStatus());
        apiDto.setContexts(api.getContexts());
        apiDto.setToken(api.getToken());
        apiDto.setDb(api.isDb());
        apiDto.setDiskspace(api.isDiskspace());
        apiDto.setPing(api.isPing());
        apiDto.setAnomalieList(api.getAnomalies());
        return apiDto;
    }

    public List<ApiDto> entitiesToDtos(List<Api> apiList){
        return apiList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public void dtoToEntity(ApiDto oldApi, Api newApi){

        newApi.setId(oldApi.getId());
        newApi.setName(oldApi.getName());
        newApi.setDescription(oldApi.getDescription());
        newApi.setIp(oldApi.getIp());
        newApi.setToken(oldApi.getToken());
        newApi.setPort(oldApi.getPort());
        newApi.setStatus(oldApi.isStatus());
        newApi.setDb(oldApi.isDb());
        newApi.setDiskspace(oldApi.isDiskspace());
        newApi.setPing(oldApi.isPing());
        // if context lis tis empty then create new one
        if(oldApi.getContexts() == null){
            newApi.setContexts(new ArrayList<>());
        }
        // if context lis tis empty then create new one
        if(oldApi.getAnomalieList() == null){
            newApi.setAnomalies(new ArrayList<>());
        }
    }

}
