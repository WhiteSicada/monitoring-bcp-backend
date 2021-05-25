package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.TestConvertor;
import com.bcp.monitoring.dto.api.ListApisDto;
import com.bcp.monitoring.dto.test.TestDto;
import com.bcp.monitoring.dto.test.TestDtoShow;
import com.bcp.monitoring.dto.test.TestDtoUpdate;
import com.bcp.monitoring.model.Test;
import com.bcp.monitoring.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    public TestRepository testRepository;

    @Autowired
    public TestConvertor testConvertor;

    @Override
    public TestDto createTest(TestDto testDto) {
        Test test = new Test();
        testConvertor.dtoToEntity(testDto,test);
        testRepository.save(test);
        return testConvertor.entityToDto(test);
    }

    @Override
    public TestDto updateTest(Long id, TestDto testDto) {
        Optional<Test> test = testRepository.findById(id);
        if(test.isPresent()){
            testConvertor.dtoToEntity(testDto,test.get());
            testRepository.save(test.get());
            return testConvertor.entityToDto(test.get());
        }
        return null;
    }

    @Override
    public String deleteTest(Long id) {
        Optional<Test> test = testRepository.findById(id);
        if(test.isPresent()){
            testRepository.deleteById(id);
            return "Test with id: " + id + " deleted successfully!";
        }
        return "Test with id: " + id + " not deleted successfully!";
    }

    @Override
    public List<TestDto> getTestList() {
        List<Test> testList = testRepository.findAll();
        return testConvertor.entitysToDtos(testList);
    }


}
