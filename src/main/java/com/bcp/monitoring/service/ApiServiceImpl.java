package com.bcp.monitoring.service;

import com.bcp.monitoring.configuration.actuatorDeserialisation.Root;
import com.bcp.monitoring.convertor.ApiConvertor;
import com.bcp.monitoring.convertor.ContextConvertor;
import com.bcp.monitoring.convertor.EndpointConvertor;
import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.contexts.ContextUpdateDto;
import com.bcp.monitoring.dto.contexts.ListContextsIds;
import com.bcp.monitoring.dto.contexts.ListContextsObjects;
import com.bcp.monitoring.dto.contexts.LitsContextsNames;
import com.bcp.monitoring.dto.endpoint.EndpointDto;
import com.bcp.monitoring.dto.endpoint.ListEndpointDto;
import com.bcp.monitoring.dto.endpoint.ListEndpointIds;
import com.bcp.monitoring.model.Anomalie;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Context;
import com.bcp.monitoring.model.Endpoint;
import com.bcp.monitoring.repository.ApiRepository;
import com.bcp.monitoring.repository.ContextRepository;
import com.bcp.monitoring.repository.EndpointRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    public ApiConvertor apiConvertor;

    @Autowired
    public ApiRepository apiRepository;

    @Autowired
    public ContextRepository contextRepository;

    @Autowired
    public ContextConvertor contextConvertor;

    @Autowired
    public EndpointConvertor endpointConvertor;

    @Autowired
    public EndpointRepository endpointRepository;

    Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Transactional
    @Override
    public ApiDto createApi(ApiDto apiDto) {
        Api newApi = new Api();
        apiConvertor.dtoToEntity(apiDto, newApi);
        // check If the api is availbele

        if (checkAPiExists(newApi)) {
            //save
            apiRepository.save(newApi);
            return apiConvertor.entityToDto(newApi);
        }
        return null;
    }

    @Override
    public List<ApiDto> getAllApis() {
        List<Api> apiList = apiRepository.findAllByOrderByIdDesc();
        return apiConvertor.entitiesToDtos(apiList);
    }

    @Override
    public Api getApiById(Long id) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            return api.get();
        }
        return null;
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
    public Boolean checkAPiExists(Api api) {
        try {
            String url = api.getIp() + ":" + api.getPort();
            final String uri = "http://" + url + "/actuator/health";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", api.getToken());
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);
            Root root = new ObjectMapper().readValue(response.getBody(), Root.class);
            api.setPing(root.getComponents().getPing().getStatus().equals("UP") ? true : false);
            api.setDiskspace(root.getComponents().getDiskSpace().getStatus().equals("UP") ? true : false);
            api.setStatus(root.getStatus().equals("UP") ? true : false);
            api.setDb(root.getComponents().getDb().getStatus().equals("UP") ? true : false);
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
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ApiDto addContextsToApi(Long id, LitsContextsNames contextsNames) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            for (String context : contextsNames.getContexts()) {
                Optional<Context> contextCheck = contextRepository.findByName(context);
                if (!contextCheck.isPresent()) {
                    api.get().addContext(new Context(context, new ArrayList<>()));
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
    public ApiDto removeContextFromApi(Long id, ListContextsIds contextsIds) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            for (Long contextId : contextsIds.getContexts()) {
                Optional<Context> contextCheck = contextRepository.findById(contextId);
                if (contextCheck.isPresent()) {
                    api.get().removeContext(contextCheck.get());
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
    public ApiDto updateApiContexts(Long id, ListContextsObjects contextsObjects) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            for (ContextUpdateDto contextItem : contextsObjects.getContexts()) {
                // to be enhanced i want to get the associated context of the current api
                Optional<Context> oldContext = contextRepository.findById(contextItem.getId());
                if (oldContext.isPresent()) {
                    int index = api.get().getContexts().indexOf(oldContext.get());
                    oldContext.get().setName(contextItem.getName());
                    api.get().updateContext(index, oldContext.get());
                } else {
                    return null;
                }

            }
            Api saveApi = apiRepository.save(api.get());
            return apiConvertor.entityToDto(saveApi);
//            return apiConvertor.entityToDto(api.get());
        }
        return null;
    }

    @Override
    public ApiDto addEndpointsToContext(Long api_id, Long context_id, ListEndpointDto listEndpoints) {
        Optional<Api> api = apiRepository.findById(api_id);
        if (api.isPresent()) {
            Optional<Context> context = contextRepository.findById(context_id);
            if (context.isPresent()) {
                for (Endpoint endpoint : listEndpoints.getEndpoints()) {
                    Optional<Endpoint> endpointCheck = endpointRepository.findByName(endpoint.getName());
                    if (!endpointCheck.isPresent()) {
                        context.get().addEndpoint(endpoint);
                    } else {
                        return null;
                    }
                }
                contextRepository.save(context.get());
                Api savedApi = apiRepository.save(api.get());
                return apiConvertor.entityToDto(savedApi);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Context removeEndpointsFromContext(Long api_id, Long context_id, ListEndpointIds listEndpoints) {
        Optional<Api> api = apiRepository.findById(api_id);
        if (api.isPresent()) {
            Optional<Context> context = contextRepository.findById(context_id);
            if (context.isPresent()) {
                for (Long endpointId : listEndpoints.getEndpoints()) {
                    Optional<Endpoint> endpointCheck = endpointRepository.findById(endpointId);
                    if (endpointCheck.isPresent()) {
                        System.out.println(endpointCheck.toString());
                        context.get().removeEndpoint(endpointCheck.get());
                    } else {
                        return null;
                    }
                }
                Context saveContext = contextRepository.save(context.get());
                System.out.println(saveContext.toString());
                return saveContext;
            }
        }
        return null;
    }

    @Override
    public Context updateContextEndpoints(Long api_id, Long context_id, ListEndpointDto listEndpoints) {
        Optional<Api> api = apiRepository.findById(api_id);
        if (api.isPresent()) {
            Optional<Context> context = contextRepository.findById(context_id);
            if (context.isPresent()) {
                for (Endpoint endpoint : listEndpoints.getEndpoints()) {
                    Optional<Endpoint> oldEndpoint = endpointRepository.findById(endpoint.getId());
                    if (oldEndpoint.isPresent()) {
                        Endpoint newEndpoint = new Endpoint();
                        endpointConvertor.dtoToEntity(endpoint, newEndpoint);
                        context.get().updateEndpoint(oldEndpoint.get(), newEndpoint);
                    } else {
                        return null;
                    }
                }
                Context saveContext = contextRepository.save(context.get());
                return saveContext;
            }
        }
        return null;
    }

    @Override
    public List<Anomalie> getTestAnomalies(Long id) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            return api.get().getAnomalies();
        }
        return null;
    }
}
