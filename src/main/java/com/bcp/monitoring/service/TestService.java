package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.anomalie.AnomalieDto;
import com.bcp.monitoring.dto.api.ListApisDto;
import com.bcp.monitoring.dto.scan.ScanDtoShow;
import com.bcp.monitoring.dto.test.TestDto;

import java.util.List;

public interface TestService {

    TestDto createTest(TestDto testDto);
    TestDto updateTest(Long id, TestDto testDto);
    String deleteTest(Long id);
    List<TestDto> getTestList();
    List<ScanDtoShow> getTestScans(Long id);

    TestDto addApisToTest(Long id, ListApisDto listApisDto);
    TestDto removeApisFromTest(Long id, ListApisDto listApisDto);
}
