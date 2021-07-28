package com.bcp.monitoring.configuration.actuatorDeserialisation;

import lombok.Data;

public @Data
class Root {
    private String status;
    private Component components;
}
