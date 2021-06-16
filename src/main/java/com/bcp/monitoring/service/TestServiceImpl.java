package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.ScanConvertor;
import com.bcp.monitoring.convertor.TestConvertor;
import com.bcp.monitoring.dto.api.ListApisDto;
import com.bcp.monitoring.dto.scan.ScanDtoShow;
import com.bcp.monitoring.dto.test.TestDto;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Projet;
import com.bcp.monitoring.model.Scan;
import com.bcp.monitoring.model.Test;
import com.bcp.monitoring.repository.ApiRepository;
import com.bcp.monitoring.repository.ScanRepository;
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
    public ScanRepository scanRepository;

    @Autowired
    public ScanConvertor scanConvertor;

    @Autowired
    public ApiRepository apiRepository;

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
            test.get().getListAPIs().clear();
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

    @Override
    public List<ScanDtoShow> getTestScans(Long id) {
        Optional<Test> test = testRepository.findById(id);

        List<Scan> scanList = scanRepository.findAllByTest(test.get());
        return scanConvertor.entitiesToDotos(scanList);
    }

    @Override
    public TestDto addApisToTest(Long id, ListApisDto listApisDto) {
        Optional<Test> test = testRepository.findById(id);
        if(test.isPresent()){
            for (String api : listApisDto.getApis()){
                Optional<Api> apiTest = apiRepository.findByName(api);
                if (apiTest.isPresent()){
                    test.get().addAPI(apiTest.get());
                }
                else{
                    return null;
                }
            }
            Test saveTest = testRepository.save(test.get());
            return testConvertor.entityToDto(saveTest);
        }
        return null;
    }

    @Override
    public TestDto removeApisFromTest(Long id, ListApisDto listApisDto) {
        Optional<Test> test = testRepository.findById(id);
        if(test.isPresent()){
            for (String api : listApisDto.getApis()){
                Optional<Api> apiTest = apiRepository.findByName(api);
                if (apiTest.isPresent()){
                    test.get().removeAPI(apiTest.get());
                }
                else{
                    return null;
                }
            }
            Test saveTest = testRepository.save(test.get());
            return testConvertor.entityToDto(saveTest);
        }
        return null;
    }


}
