package com.bcp.monitoring.controller;

import com.bcp.monitoring.dto.ResponsableMetierDto;
import com.bcp.monitoring.service.ResponsableMetierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class ResponsableMetierController {

    @Autowired
    public ResponsableMetierService responsableMetierService;

    @PostMapping("/responsablemetier")
    public ResponseEntity<ResponsableMetierDto> createResponsableMetier(@RequestBody ResponsableMetierDto responsableMetierDto){
        ResponsableMetierDto responsableMetier = responsableMetierService.createResponsableMetier(responsableMetierDto);
        return new ResponseEntity<>(responsableMetier, HttpStatus.CREATED);
    }

    @PutMapping("/responsablemetier/{id}")
    public ResponseEntity<ResponsableMetierDto> updateResponsableMetier(@PathVariable(name = "id") Long id,
                                                  @RequestBody ResponsableMetierDto responsableMetierDto){
        ResponsableMetierDto updatedResponsableMetier = responsableMetierService.updateResponsableMetier(id,responsableMetierDto);
        return new ResponseEntity<>(updatedResponsableMetier,HttpStatus.OK);
    }

    @DeleteMapping("/responsablemetier/{id}")
    public ResponseEntity<String> deleteResponsableMetier(@PathVariable(name = "id") Long id){
        String message = responsableMetierService.deleteResponsableMetier(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/responsablemetiers")
    public ResponseEntity<List<ResponsableMetierDto>> getAllResponsableMetiers(){
        List<ResponsableMetierDto> responsableMetierDtos = responsableMetierService.getAllResponsableMetiersIt();
        return new ResponseEntity<>(responsableMetierDtos, HttpStatus.OK);
    }
}
