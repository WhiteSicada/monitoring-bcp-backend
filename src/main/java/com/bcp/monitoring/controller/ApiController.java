package com.bcp.monitoring.controller;

import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.contexts.ListContextsIds;
import com.bcp.monitoring.dto.contexts.ListContextsObjects;
import com.bcp.monitoring.dto.contexts.LitsContextsNames;
import com.bcp.monitoring.dto.endpoint.ListEndpointDto;
import com.bcp.monitoring.dto.endpoint.ListEndpointIds;
import com.bcp.monitoring.model.Anomalie;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Context;
import com.bcp.monitoring.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class ApiController {

    @Autowired
    public ApiService apiService;


    @PostMapping("/api")
    public ResponseEntity<?> createApi(@RequestBody ApiDto apiDto){
        ApiDto apiDtoShow = apiService.createApi(apiDto);
        return apiDtoShow == null
                ? new ResponseEntity<>("cannot create Api", HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(apiDtoShow,HttpStatus.CREATED);
    }

    @GetMapping("/apis")
    public ResponseEntity<List<ApiDto>> getAllApis(){
        List<ApiDto> apiDtoShows = apiService.getAllApis();
        return new ResponseEntity<>(apiDtoShows,HttpStatus.OK);
    }

    @GetMapping("/apis/{id}")
    public ResponseEntity<Api> getApiById(@PathVariable(name = "id") Long id){
        Api api = apiService.getApiById(id);
        return new ResponseEntity<>(api,HttpStatus.OK);
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<String> deleteApi(@PathVariable(name = "id") Long id){
        String message = apiService.deleteApi(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PutMapping("/api/{id}")
    public ResponseEntity<Long> updateApi(@PathVariable(name = "id") Long id,
                                                @RequestBody ApiDto apiDto){
        Long updatedApi = apiService.updateApi(id, apiDto);
        return new ResponseEntity<>(updatedApi,HttpStatus.OK);
    }

    @PutMapping("/api/{id}/addContexts")
    public ResponseEntity<?> addContextsToApi(@PathVariable(name = "id") Long id, @RequestBody LitsContextsNames contextsNames){
        ApiDto updateApi = apiService.addContextsToApi(id,contextsNames);
        if (updateApi == null){
            return new ResponseEntity<>("cannot add Context",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updateApi,HttpStatus.OK);
    }

    @PutMapping("/api/{id}/removeContexts")
    public ResponseEntity<?> removeContextFromApi(@PathVariable(name = "id") Long id, @RequestBody ListContextsIds contextsIds){
        ApiDto updateApi = apiService.removeContextFromApi(id,contextsIds);
        if (updateApi == null){
            return new ResponseEntity<>("cannot remove Context",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updateApi,HttpStatus.OK);
    }

    @PutMapping("/api/{id}/updateContexts")
    public ResponseEntity<?> updateApiContexts(@PathVariable(name = "id") Long id, @RequestBody ListContextsObjects contextsObjects){
        ApiDto updateApi = apiService.updateApiContexts(id,contextsObjects);
        if (updateApi == null){
            return new ResponseEntity<>("cannot update Context",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updateApi,HttpStatus.OK);
    }

    @GetMapping("/api/{id}/getAnomalies")
    public ResponseEntity<List<Anomalie>> getApiAnomalies(@PathVariable(name = "id") Long id){
        List<Anomalie> anomalieDtos = apiService.getTestAnomalies(id);
        return new ResponseEntity<>(anomalieDtos, HttpStatus.OK);
    }

    // add endpoints to context of an api
    @PostMapping("/api/{api_id}/context/{context_id}/addEndpoints")
    public ResponseEntity<?> addEndpointsToContext(
            @PathVariable(name = "api_id") Long api_id,
            @PathVariable(name = "context_id") Long context_id,
            @RequestBody ListEndpointDto listEndpoints
    ){
        ApiDto apiDto = apiService.addEndpointsToContext(api_id,context_id,listEndpoints);
        if (apiDto == null){
            return new ResponseEntity<>("cannot add Endpoints To context",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(apiDto,HttpStatus.OK);
    }

    // remove endpoints from context of an api
    @PutMapping("/api/{api_id}/context/{context_id}/removeEndpoints")
    public ResponseEntity<?> removeEndpointsFromContext(
            @PathVariable(name = "api_id") Long api_id,
            @PathVariable(name = "context_id") Long context_id,
            @RequestBody ListEndpointIds endpoints
    ){
        Context context = apiService.removeEndpointsFromContext(api_id,context_id,endpoints);
        if (context == null){
            return new ResponseEntity<>("cannot remove Endpoints from context",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("context",HttpStatus.OK);

    }

    // update endpoints of context of an api
    @PutMapping("/api/{api_id}/context/{context_id}/updateEndpoints")
    public ResponseEntity<?> updateContextEndpoints(
            @PathVariable(name = "api_id") Long api_id,
            @PathVariable(name = "context_id") Long context_id,
            @RequestBody ListEndpointDto listEndpoints
    ){
        Context context = apiService.updateContextEndpoints(api_id,context_id,listEndpoints);
        if (context == null){
            return new ResponseEntity<>("cannot remove Endpoints To context",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(context,HttpStatus.OK);
    }

}