package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.api.ApiDtoShow;

import java.util.List;

public interface ApiService {

    ApiDtoShow createApi(ApiDto apiDto);

    List<ApiDtoShow> getAllApis();

    ApiDtoShow updateApi(Long id, ApiDto apiDto);

    String deleteApi(Long id);

    Boolean checkAPiExists(String url);
}
