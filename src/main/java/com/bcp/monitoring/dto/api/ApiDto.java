package com.bcp.monitoring.dto.api;

import com.bcp.monitoring.model.Endpoint;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class ApiDto {
    private Long id;
    private String name;
    private String description;
    private String ip;
    private String context;
    private int port;
    private List<Endpoint> endpointList = new ArrayList<>();
}
