package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.ApiConvertor;
import com.bcp.monitoring.convertor.EndpointConvertor;
import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.api.ApiDtoShow;
import com.bcp.monitoring.dto.endpoint.EndpointDto;
import com.bcp.monitoring.dto.endpoint.ListEndpointDto;
import com.bcp.monitoring.dto.endpoint.ListEndpointIds;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Endpoint;
import com.bcp.monitoring.repository.ApiRepository;
import com.bcp.monitoring.repository.EndpointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    public ApiConvertor apiConvertor;

    @Autowired
    public ApiRepository apiRepository;

    @Autowired
    public EndpointRepository endpointRepository;

    @Autowired
    public EndpointConvertor endpointConvertor;

    Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Transactional
    @Override
    public ApiDtoShow createApi(ApiDto apiDto) {
        Api api = new Api();
        apiConvertor.dtoToEntity(apiDto, api);
        // check If the api is availbele
        String url = api.getIp() + ":" + api.getPort();
        if (checkAPiExists(url)) {
            //save
            apiRepository.save(api);
            return apiConvertor.entityToDto(api);
        }
        return null;
    }

    @Override
    public List<ApiDtoShow> getAllApis() {
        List<Api> apiList = apiRepository.findAll();
        return apiConvertor.entitiesToDtos(apiList);
    }

    @Transactional
    @Override
    public Long updateApi(Long id, ApiDto apiDto) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            apiConvertor.dtoToEntity(apiDto, api.get());
            Api apiSaved = apiRepository.save(api.get());
            return apiSaved.getId();
        }
        return null;
    }

    @Override
    public String deleteApi(Long id) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            apiRepository.deleteById(api.get().getId());
            return "Api with id: " + id + " deleted successfully!";
        }
        return "Api with id: " + id + " not deleted successfully!";
    }

    @Override
    public Boolean checkAPiExists(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            final String uri = "http://" + url + "/actuator/health";
            String result = restTemplate.getForObject(uri, String.class);
            System.out.println(result);
        } catch (HttpStatusCodeException ex) {
            // raw http status code e.g `404`
            System.out.println(ex.getRawStatusCode());
            // http status code e.g. `404 NOT_FOUND`
            System.out.println(ex.getStatusCode().toString());
            // get response body
            System.out.println(ex.getResponseBodyAsString());
            // get http headers
            HttpHeaders headers = ex.getResponseHeaders();
            System.out.println(headers.get("Content-Type"));
            System.out.println(headers.get("Server"));
        }
        return true;
    }

    @Override
    public ApiDtoShow addEndpointToApi(Long id, ListEndpointDto endpoints) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            for (EndpointDto endpointDto : endpoints.getEndpoints()) {
                Optional<Endpoint> endpointCheck = endpointRepository.findByName(endpointDto.getName());
                if (!endpointCheck.isPresent()) {
                    Endpoint endpoint = new Endpoint();
                    endpointConvertor.dtoToEntity(endpointDto, endpoint);
                    api.get().addEndpoint(endpoint);
                } else {
                    return null;
                }
            }
            Api saveApi = apiRepository.save(api.get());
            return apiConvertor.entityToDto(saveApi);
        }
        return null;
    }

    @Override
    public ApiDtoShow removeEndpointFromApi(Long id, ListEndpointIds endpoints) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            for (Long endpointId : endpoints.getEndpoints()) {
                Optional<Endpoint> endpointCheck = endpointRepository.findById(endpointId);
                if (endpointCheck.isPresent()) {
                    api.get().removeEndpoint(endpointCheck.get());
                } else {
                    return null;
                }
            }
            Api saveApi = apiRepository.save(api.get());
            return apiConvertor.entityToDto(saveApi);
        }
        return null;
    }

    @Override
    public ApiDtoShow updateApiEndpoints(Long id, ListEndpointDto endpoints) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            for (EndpointDto endpointDto : endpoints.getEndpoints()) {

                // to be enhanced i want to get the associated endpoint of the current api
                Optional<Endpoint> endpointCheck = endpointRepository.findById(endpointDto.getId());
                if (endpointCheck.isPresent()) {
                    Endpoint endpoint = new Endpoint();
                    endpointConvertor.dtoToEntity(endpointDto, endpoint);
                    api.get().updateEndpoint(endpointCheck.get(), endpoint);
                    logger.info(api.get().getEndpoints().toString());
                } else {
                    return null;
                }
            }
            Api saveApi = apiRepository.save(api.get());
            return apiConvertor.entityToDto(saveApi);
        }
        return null;
    }
}
