package com.bcp.monitoring.dto.endpoint;

import lombok.Data;

import java.util.Arrays;

public @Data class ListEndpointIds {
        private Long[] endpoints;

    @Override
    public String toString() {
        return "ListEndpointIds{" +
                "endpoints=" + Arrays.toString(endpoints) +
                '}';
    }
}
