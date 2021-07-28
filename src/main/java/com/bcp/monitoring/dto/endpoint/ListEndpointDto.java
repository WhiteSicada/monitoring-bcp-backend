package com.bcp.monitoring.dto.endpoint;

import com.bcp.monitoring.model.Endpoint;
import lombok.Data;

import java.util.Arrays;

public @Data
class ListEndpointDto {
    private Endpoint[] endpoints;

    @Override
    public String toString() {
        return "ListEndpointDto{" +
                "endpoints=" + Arrays.toString(endpoints) +
                '}';
    }
}
