package com.bcp.monitoring.dto;

import lombok.Data;

public @Data class EndpointDto {
    private Long id;
    private String name;
    private String url;
    private String method;
    private String data;
}
