package com.bcp.monitoring.controller;

import com.bcp.monitoring.dto.ResponsableItDto;
import com.bcp.monitoring.service.ResponsableITService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class ResponsableITController {

    @Autowired
    public ResponsableITService responsableITService;

    @PostMapping("/responsableit")
    public ResponseEntity<ResponsableItDto> createResponsableIt(@RequestBody ResponsableItDto responsableItDto){
        ResponsableItDto responsableItDto1 = responsableITService.createResponsableIt(responsableItDto);
        return new ResponseEntity<>(responsableItDto1, HttpStatus.CREATED);
    }

    @PutMapping("/responsableit/{id}")
    public ResponseEntity<ResponsableItDto> updateResponsableIt(@PathVariable(name = "id") Long id,
                                                  @RequestBody ResponsableItDto responsableItDto){
        ResponsableItDto updatedUesponsableItDto = responsableITService.updateResponsableIt(id,responsableItDto);
        return new ResponseEntity<>(updatedUesponsableItDto,HttpStatus.OK);
    }

    @DeleteMapping("/responsableit/{id}")
    public ResponseEntity<String> deleteResponsableIt(@PathVariable(name = "id") Long id){
        String message = responsableITService.deleteResponsibleIt(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/responsableits")
    public ResponseEntity<List<ResponsableItDto>> getAllResponsablesIt(){
        List<ResponsableItDto> responsableItDtos = responsableITService.getAllResponsablesIt();
        return new ResponseEntity<>(responsableItDtos, HttpStatus.OK);
    }
}
