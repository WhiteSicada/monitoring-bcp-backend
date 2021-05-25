package com.bcp.monitoring.controller;

import com.bcp.monitoring.dto.EquipeDto;
import com.bcp.monitoring.dto.test.TestDto;
import com.bcp.monitoring.dto.test.TestDtoShow;
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
}
