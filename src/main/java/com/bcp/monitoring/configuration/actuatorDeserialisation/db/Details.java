package com.bcp.monitoring.configuration.actuatorDeserialisation.db;

import lombok.Data;

public @Data
class Details {
    private String database;
    private String validationQuery;
}
