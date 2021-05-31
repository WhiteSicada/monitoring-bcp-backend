package com.bcp.monitoring.dto.api;

import com.bcp.monitoring.model.Anomalie;
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
    private boolean status = false;
    private boolean db = false;
    private boolean diskspace = false;
    private boolean ping = false;
    private List<Endpoint> endpointList = new ArrayList<>();
    private List<Anomalie> anomalieList = new ArrayList<>();
}
