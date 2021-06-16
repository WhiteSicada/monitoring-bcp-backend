package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.scan.ScanDtoShow;

import java.util.List;

public interface ScanSerive {

    List<ScanDtoShow> scanTest(Long id);
    List<ScanDtoShow> getAllScans();
}
