package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.api.ListApisDto;
import com.bcp.monitoring.dto.test.TestDto;
import com.bcp.monitoring.dto.test.TestDtoShow;

import java.util.List;

public interface TestService {

    TestDtoShow createTest(TestDto testDto);
//    TestDto updateTest(Long id, TestDto testDto);
//    String deleteTest(Long id);
//    List<TestDto> getTestList();
//    TestDto addApisToTest(Long id, ListApisDto listApisDto);
//    TestDto removeApisFromTest(Long id, ListApisDto apis);
}
