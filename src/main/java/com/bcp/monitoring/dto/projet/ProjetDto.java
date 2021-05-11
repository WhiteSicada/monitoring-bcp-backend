package com.bcp.monitoring.dto.projet;

import com.bcp.monitoring.model.Api;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

public @Data class ProjetDto {
    private Long id;
    private String name;
    private String responsableIt;
    private String responsableMetier;
    private String equipe;
    private String description;
    private Set<Api> listAPIs = new HashSet<>();
}
