package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.api.ApiDtoShow;
import com.bcp.monitoring.dto.endpoint.ListEndpointDto;
import com.bcp.monitoring.dto.endpoint.ListEndpointIds;

import java.util.List;

public interface ApiService {

    ApiDtoShow createApi(ApiDto apiDto);

    List<ApiDtoShow> getAllApis();

    ApiDtoShow updateApi(Long id, ApiDto apiDto);

    String deleteApi(Long id);

    Boolean checkAPiExists(String url);

    ApiDtoShow addEndpointToApi(Long id, ListEndpointDto endpoints);

    ApiDtoShow removeEndpointFromApi(Long id, ListEndpointIds endpoints);

    ApiDtoShow updateApiEndpoints(Long id, ListEndpointDto endpoints);
}
