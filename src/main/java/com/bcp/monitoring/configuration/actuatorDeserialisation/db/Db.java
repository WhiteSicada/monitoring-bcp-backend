package com.bcp.monitoring.configuration.actuatorDeserialisation.db;

import lombok.Data;

public @Data
class Db {
    private String status;
    private Details details;
}
