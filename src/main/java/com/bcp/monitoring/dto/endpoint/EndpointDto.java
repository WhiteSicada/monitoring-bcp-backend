package com.bcp.monitoring.dto.endpoint;

import lombok.Data;

public @Data class EndpointDto {
    private Long id;
    private String name;
    private String url;
    private String method;
    private String data;
}
