package com.bcp.monitoring.controller;

import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.api.ApiDtoShow;
import com.bcp.monitoring.dto.endpoint.ListEndpointDto;
import com.bcp.monitoring.dto.endpoint.ListEndpointIds;
import com.bcp.monitoring.model.Anomalie;
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
        ApiDtoShow apiDtoShow = apiService.createApi(apiDto);
        return apiDtoShow == null
                ? new ResponseEntity<>("cannot create Projet", HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(apiDtoShow,HttpStatus.CREATED);
    }

    @GetMapping("/apis")
    public ResponseEntity<List<ApiDtoShow>> getAllApis(){
        List<ApiDtoShow> apiDtoShows = apiService.getAllApis();
        return new ResponseEntity<>(apiDtoShows,HttpStatus.OK);
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

    @PutMapping("/api/{id}/addEndpoints")
    public ResponseEntity<?> addEndpointsToApi(@PathVariable(name = "id") Long id, @RequestBody ListEndpointDto endpoints){
        ApiDtoShow updateApi = apiService.addEndpointToApi(id,endpoints);
        if (updateApi == null){
            return new ResponseEntity<>("cannot add Endpoint",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updateApi,HttpStatus.OK);
    }

    @PutMapping("/api/{id}/removeEndpoints")
    public ResponseEntity<?> removeEndpointFromApi(@PathVariable(name = "id") Long id, @RequestBody ListEndpointIds endpoints){
        ApiDtoShow updateApi = apiService.removeEndpointFromApi(id,endpoints);
        if (updateApi == null){
            return new ResponseEntity<>("cannot remove Endpoint",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updateApi,HttpStatus.OK);
    }

    @PutMapping("/api/{id}/updateEndpoints")
    public ResponseEntity<?> updateEndpointsForApi(@PathVariable(name = "id") Long id, @RequestBody ListEndpointDto endpoints){
        ApiDtoShow updateApi = apiService.updateApiEndpoints(id,endpoints);
        if (updateApi == null){
            return new ResponseEntity<>("cannot update Endpoint",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updateApi,HttpStatus.OK);
    }

    @GetMapping("/api/{id}/getAnomalies")
    public ResponseEntity<List<Anomalie>> getApiAnomalies(@PathVariable(name = "id") Long id){
        List<Anomalie> anomalieDtos = apiService.getTestAnomalies(id);
        return new ResponseEntity<>(anomalieDtos, HttpStatus.OK);
    }
}