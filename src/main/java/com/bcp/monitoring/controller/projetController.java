package com.bcp.monitoring.controller;
import com.bcp.monitoring.dto.ProjetDto;
import com.bcp.monitoring.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class projetController {

    @Autowired
    public ProjetService projetService;

    @PostMapping("/projet")
    public ResponseEntity<ProjetDto> createProject(@RequestBody ProjetDto projetDto) {
        ProjetDto projet = projetService.createProjet(projetDto);
        return new ResponseEntity<>(projet, HttpStatus.CREATED);
    }
}
