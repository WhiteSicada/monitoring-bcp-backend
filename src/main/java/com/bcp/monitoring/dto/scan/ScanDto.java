package com.bcp.monitoring.dto.scan;

import lombok.Data;

public @Data class ScanDto {
    private Long id;
    private Long api_id;
    private Long test_id;
    private Long endpoint_id;
    private String status = "Completed";
    private String spark = "Automatically";
    private String execution_time;
    private String creates_at;
}
