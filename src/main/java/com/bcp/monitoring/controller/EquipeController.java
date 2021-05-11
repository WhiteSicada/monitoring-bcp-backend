package com.bcp.monitoring.controller;

import com.bcp.monitoring.dto.EquipeDto;
import com.bcp.monitoring.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class EquipeController {

    @Autowired
    public EquipeService equipeService;


    @PostMapping("/equipe")
    public ResponseEntity<EquipeDto> createEquipe(@RequestBody EquipeDto equipeDto){
        EquipeDto equipe = equipeService.createEquipe(equipeDto);
        return new ResponseEntity<>(equipe, HttpStatus.CREATED);
    }

    @PutMapping("/equipe/{id}")
    public ResponseEntity<EquipeDto> updateEquipe(@PathVariable(name = "id") Long id,
                                                  @RequestBody EquipeDto equipeDto){
        EquipeDto equipeUpdated = equipeService.updateEquipe(id,equipeDto);
        return new ResponseEntity<>(equipeUpdated,HttpStatus.OK);
    }

    @DeleteMapping("/equipe/{id}")
    public ResponseEntity<String> deleteEquipe(@PathVariable(name = "id") Long id){
        String message = equipeService.deleteEquipe(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/equipes")
    public ResponseEntity<List<EquipeDto>> getAllEquipes(){
        List<EquipeDto> equipeDtoList = equipeService.getEquipeList();
        return new ResponseEntity<>(equipeDtoList, HttpStatus.OK);
    }
}
