package com.bcp.monitoring.dto.api;

import com.bcp.monitoring.dto.EndpointDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class ApiDto {
    private Long id;
    private String name;
    private String description;
    private String ip;
    private int port;
    private List<EndpointDto> endpointList = new ArrayList<>();
}
