package com.bcp.monitoring.dto;

import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Equipe;
import com.bcp.monitoring.model.ResponsableIt;
import com.bcp.monitoring.model.ResponsableMetier;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

public @Data class ProjetDto {
    private Long id;
    private String name;
    private ResponsableIt responsableIt;
    private ResponsableMetier responsableMetier;
    private Equipe equipe;
    private String description;
    private Set<Api> listAPIs = new HashSet<>();
}
