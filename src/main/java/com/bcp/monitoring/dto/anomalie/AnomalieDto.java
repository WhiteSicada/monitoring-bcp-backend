package com.bcp.monitoring.dto.anomalie;

import lombok.Data;

public @Data
class AnomalieDto {
    private Long id;
    private String erreur;
    private String date;
    private boolean fixed;
}
