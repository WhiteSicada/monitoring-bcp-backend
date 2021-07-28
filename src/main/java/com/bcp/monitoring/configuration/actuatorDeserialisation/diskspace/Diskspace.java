package com.bcp.monitoring.configuration.actuatorDeserialisation.diskspace;

import lombok.Data;

public @Data class Diskspace {
    private String status;
    private Details details;
}
