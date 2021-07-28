package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.EndpointConvertor;
import com.bcp.monitoring.dto.endpoint.EndpointDto;
import com.bcp.monitoring.model.Endpoint;
import com.bcp.monitoring.repository.EndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EndpointServiceImpl implements EndpointService {

    @Autowired
    public EndpointConvertor endpointConvertor;

    @Autowired
    public EndpointRepository endpointRepository;

    @Override
    public EndpointDto createEndpoint(Endpoint endpoint) {
        Endpoint savedEndpoint = endpointRepository.save(endpoint);
        return endpointConvertor.entityToDoto(savedEndpoint);
    }

    @Override
    public EndpointDto updateEndpoint(Long id, Endpoint endpointDto) {
        Optional<Endpoint> endpoint = endpointRepository.findById(id);
        if (endpoint.isPresent()) {
            endpointConvertor.dtoToEntity(endpointDto, endpoint.get());
            endpointRepository.save(endpoint.get());
            return endpointConvertor.entityToDoto(endpoint.get());
        }
        return null;
    }

    @Override
    public String deleteEndpoint(Long id) {
        Optional<Endpoint> endpoint = endpointRepository.findById(id);
        if (endpoint.isPresent()) {
            endpointRepository.deleteById(endpoint.get().getId());
            return "Endpoint with id: " + id + " deleted successfully!";
        }
        return "Endpoint with id: " + id + " not deleted successfully!";
    }

    @Override
    public List<EndpointDto> getEndpointList() {
        List<Endpoint> endpoints = endpointRepository.findAll();
        return endpointConvertor.entitiesToDotos(endpoints);
    }
}
