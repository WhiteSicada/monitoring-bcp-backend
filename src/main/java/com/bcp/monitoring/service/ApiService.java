package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.contexts.ListContextsIds;
import com.bcp.monitoring.dto.contexts.ListContextsObjects;
import com.bcp.monitoring.dto.contexts.LitsContextsNames;
import com.bcp.monitoring.dto.endpoint.ListEndpointDto;
import com.bcp.monitoring.dto.endpoint.ListEndpointIds;
import com.bcp.monitoring.model.Anomalie;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Context;

import java.util.List;

public interface ApiService {

    ApiDto createApi(ApiDto apiDto);

    List<ApiDto> getAllApis();

    Api getApiById(Long id);

    Long updateApi(Long id, ApiDto apiDto);

    String deleteApi(Long id);

    Boolean checkAPiExists(Api api);

    ApiDto addContextsToApi(Long id, LitsContextsNames contextsNames);

    ApiDto removeContextFromApi(Long id, ListContextsIds contextsIds);

    ApiDto updateApiContexts(Long id, ListContextsObjects contextsObjects);

    ApiDto addEndpointsToContext(Long api_id, Long context_id, ListEndpointDto listEndpoints);

    Context removeEndpointsFromContext(Long api_id,Long context_id, ListEndpointIds listEndpoints);

    Context updateContextEndpoints(Long api_id,Long context_id, ListEndpointDto listEndpoints);

    List<Anomalie> getTestAnomalies(Long id);
}
