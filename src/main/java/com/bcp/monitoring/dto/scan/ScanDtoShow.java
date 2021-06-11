package com.bcp.monitoring.dto.scan;

import lombok.Data;

public @Data
class ScanDtoShow {
    private Long id;
    private String api;
    private String test;
    private String endpoint;
    private String status;
    private String spark;
    private String creates_at;
}
