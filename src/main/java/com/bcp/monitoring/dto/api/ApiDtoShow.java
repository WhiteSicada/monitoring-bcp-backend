package com.bcp.monitoring.dto.api;

import com.bcp.monitoring.model.Anomalie;
import com.bcp.monitoring.model.Endpoint;
import lombok.Data;

import java.util.List;

public @Data class ApiDtoShow {
    private Long id;
    private String name;
    private String description;
    private String ip;
    private int port;
    private boolean status;
    private boolean db;
    private boolean diskspace;
    private boolean ping;
//    private Set<Projet> listProjets = new HashSet<>();
    private List<Anomalie> anomalies;
    private List<Endpoint> endpoints;
}
