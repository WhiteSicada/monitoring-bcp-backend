package com.bcp.monitoring.controller;

import com.bcp.monitoring.dto.projet.ProjetDto;
import com.bcp.monitoring.dto.projet.ProjetDtoShow;
import com.bcp.monitoring.dto.test.TestDto;
import com.bcp.monitoring.dto.test.TestDtoShow;
import com.bcp.monitoring.repository.TestRepository;
import com.bcp.monitoring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TestController {

    @Autowired
    public TestService testService;

    @PostMapping("/test")
    public ResponseEntity<?> createProject(@RequestBody TestDto testDto) {
        TestDtoShow test = testService.createTest(testDto);
        if (test == null){
            return new ResponseEntity<>("cannot create Test", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }
}
