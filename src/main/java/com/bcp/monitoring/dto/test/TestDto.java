package com.bcp.monitoring.dto.test;

import com.bcp.monitoring.model.Api;
import lombok.Data;

import java.util.List;

public @Data
class TestDto {
    private Long id;
    private String name;
    private Long interval;
    private List<Api> listAPIs;
}
