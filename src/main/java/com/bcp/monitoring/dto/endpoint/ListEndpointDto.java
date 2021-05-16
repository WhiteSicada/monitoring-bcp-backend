package com.bcp.monitoring.dto.endpoint;

import com.bcp.monitoring.model.Endpoint;
import lombok.Data;

public @Data
class ListEndpointDto {
    private Endpoint[] endpoints;
}
