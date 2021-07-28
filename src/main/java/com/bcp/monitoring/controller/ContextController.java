package com.bcp.monitoring.controller;

import com.bcp.monitoring.dto.contexts.LitsContextsNames;
import com.bcp.monitoring.model.Context;
import com.bcp.monitoring.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class ContextController {

    @Autowired
    public ContextService contextService;



    @PutMapping("/contexts/{id}")
    public ResponseEntity<?> createContexts(@PathVariable(name = "id") Long id,
                                            @RequestBody Context oldContext){
        Context newContext = contextService.updateContext(id,oldContext);
        return new ResponseEntity<>(newContext, HttpStatus.OK);
    }

    @DeleteMapping("/contexts/{id}")
    public ResponseEntity<?> deleteContext(@PathVariable(name = "id") Long id){
        String message = contextService.deleteContext(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
