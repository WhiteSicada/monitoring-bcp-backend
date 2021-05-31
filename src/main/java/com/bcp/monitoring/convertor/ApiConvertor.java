package com.bcp.monitoring.convertor;

import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.api.ApiDtoShow;
import com.bcp.monitoring.model.Anomalie;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Endpoint;
import com.bcp.monitoring.repository.AnomalieRepository;
import com.bcp.monitoring.repository.EndpointRepository;
import com.bcp.monitoring.service.ApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public ApiDtoShow entityToDto(Api api){
        ApiDtoShow apiDtoShow = new ApiDtoShow();
        apiDtoShow.setId(api.getId());
        apiDtoShow.setName(api.getName());
        apiDtoShow.setDescription(api.getDescription());
        apiDtoShow.setIp(api.getIp());
        apiDtoShow.setPort(api.getPort());
        apiDtoShow.setStatus(api.isStatus());
        apiDtoShow.setContext(api.getContext());
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

    public void dtoToEntity(ApiDto apiDto, Api api){

        api.setId(apiDto.getId());
        api.setName(apiDto.getName());
        api.setDescription(apiDto.getDescription());
        api.setIp(apiDto.getIp());
        api.setContext(apiDto.getContext());
        api.setPort(apiDto.getPort());
        api.setStatus(apiDto.isStatus());
        api.setDb(apiDto.isDb());
        api.setDiskspace(apiDto.isDiskspace());
        api.setPing(apiDto.isPing());
        if(apiDto.getEndpointList() == null){
            api.setEndpoints(new ArrayList<>());
        }
//        else {
//            apiDto.getEndpointList().stream().forEach(endpointItem -> {
//                Optional<Endpoint> endpoint = endpointRepository.findByName(endpointItem.getName());
//                if(endpoint.isPresent()){
//                    api.addEndpoint(endpoint.get());
//                }
//            });
//        }
//        if(apiDto.getAnomalieList() == null){
//            api.setAnomalies(new ArrayList<>());
//        }else {
//            apiDto.getAnomalieList().stream().forEach(anomalieItem -> {
//                Optional<Anomalie> anomalie = anomalieRepository.findByName(anomalieItem.getName());
//                if(anomalie.isPresent()){
//                    api.add(anomalie.get());
//                }
//            });
//        }
    }

}
