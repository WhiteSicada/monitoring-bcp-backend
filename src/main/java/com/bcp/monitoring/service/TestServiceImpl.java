package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.TestConvertor;
import com.bcp.monitoring.dto.api.ListApisDto;
import com.bcp.monitoring.dto.test.TestDto;
import com.bcp.monitoring.dto.test.TestDtoShow;
import com.bcp.monitoring.model.Test;
import com.bcp.monitoring.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    public TestRepository testRepository;

    @Autowired
    public TestConvertor testConvertor;

    @Override
    public TestDtoShow createTest(TestDto testDto) {
        Test test = new Test();
        testConvertor.dtoToEntity(testDto,test);
        testRepository.save(test);
        return testConvertor.entityToDto(test);
    }


}
