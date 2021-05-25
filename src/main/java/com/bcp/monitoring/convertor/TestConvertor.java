package com.bcp.monitoring.convertor;

import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.projet.ProjetDtoShow;
import com.bcp.monitoring.dto.test.TestDto;
import com.bcp.monitoring.dto.test.TestDtoShow;
import com.bcp.monitoring.model.*;
import com.bcp.monitoring.repository.ApiRepository;
import com.bcp.monitoring.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TestConvertor {
    @Autowired
    public TestRepository testRepository;

    @Autowired
    public ApiRepository apiRepository;

    public TestDtoShow entityToDto(Test test){
        // init our projetDto
        TestDtoShow testDtoShow = new TestDtoShow();
        // set projetDto infos
        testDtoShow.setId(test.getId());
        testDtoShow.setName(test.getName());
        testDtoShow.setInterval(test.getInterval());
        testDtoShow.setApiList(test.getListAPIs());
        // return projetDto
        return testDtoShow;
    }

    // Convert list of Project Entities to a list of Project DTOs
    public List<TestDtoShow> entitysToDtos(List<Test> testList){
        return testList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public void dtoToEntity(TestDto testDto, Test test){
        test.setId(testDto.getId());
        test.setName(testDto.getName());
        test.setInterval(testDto.getInterval());
        if(test.getListAPIs() == null){
            test.setListAPIs(new ArrayList<>());
        }
       for(String api : testDto.getApiList()){
           Optional<Api> apiTest = apiRepository.findByName(api);
           if (apiTest.isPresent()){
               test.addAPI(apiTest.get());
           }
       }
    }
}
