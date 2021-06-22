package com.bcp.monitoring.controller;

import com.bcp.monitoring.dto.anomalie.AnomalieDto;
import com.bcp.monitoring.dto.api.ListApisDto;
import com.bcp.monitoring.dto.projet.ProjetDtoShow;
import com.bcp.monitoring.dto.scan.ScanDtoShow;
import com.bcp.monitoring.dto.test.TestDto;
import com.bcp.monitoring.model.Test;
import com.bcp.monitoring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TestController {

    @Autowired
    public TestService testService;

    @PostMapping("/test")
    public ResponseEntity<?> createProject(@RequestBody TestDto testDto) {
        TestDto test = testService.createTest(testDto);
        if (test == null){
            return new ResponseEntity<>("cannot create Test", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    @DeleteMapping("/test/{id}")
    public ResponseEntity<String> deleteApi(@PathVariable(name = "id") Long id){
        String message = testService.deleteTest(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PutMapping("/test/{id}")
    public ResponseEntity<TestDto> updateApi(@PathVariable(name = "id") Long id,
                                                @RequestBody TestDto testDto){
        TestDto testDtoShow = testService.updateTest(id, testDto);
        return new ResponseEntity<>(testDtoShow,HttpStatus.OK);
    }

    @GetMapping("/tests")
    public ResponseEntity<List<TestDto>> getAllTests(){
        List<TestDto> testDtos = testService.getTestList();
        return new ResponseEntity<>(testDtos, HttpStatus.OK);
    }

    @GetMapping("/tests/{id}/scans")
    public ResponseEntity<List<ScanDtoShow>> getTestScans(@PathVariable(name = "id") Long id){
        List<ScanDtoShow> scanDtoShows = testService.getTestScans(id);
        return new ResponseEntity<>(scanDtoShows, HttpStatus.OK);
    }



    @PutMapping("/test/{id}/addApis")
    public ResponseEntity<?> addApisToTest(@PathVariable(name = "id") Long id, @RequestBody ListApisDto apis){
        TestDto test = testService.addApisToTest(id,apis);
        if (test == null){
            return new ResponseEntity<>("cannot add apis to test test",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test,HttpStatus.OK);
    }

    @PutMapping("/test/{id}/removeApis")
    public ResponseEntity<?> removeApisFromTest(@PathVariable(name = "id") Long id, @RequestBody ListApisDto apis){
        TestDto test = testService.removeApisFromTest(id,apis);
        if (test == null){
            return new ResponseEntity<>("cannot update Projet",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test,HttpStatus.OK);
    }
}
