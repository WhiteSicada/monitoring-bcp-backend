package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.endpoint.EndpointDto;

import java.util.List;

public interface EndpointService {

    EndpointDto createEndpoint(EndpointDto endpointDto);

    EndpointDto updateEndpoint(Long id, EndpointDto endpointDto);

    String deleteEndpoint(Long id);

    List<EndpointDto> getEndpointList();
}
