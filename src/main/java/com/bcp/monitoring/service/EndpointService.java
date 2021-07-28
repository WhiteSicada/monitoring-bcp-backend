package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.endpoint.EndpointDto;
import com.bcp.monitoring.model.Endpoint;

import java.util.List;

public interface EndpointService {

    EndpointDto createEndpoint(Endpoint endpoint);

    EndpointDto updateEndpoint(Long id, Endpoint endpoint);

    String deleteEndpoint(Long id);

    List<EndpointDto> getEndpointList();

}
