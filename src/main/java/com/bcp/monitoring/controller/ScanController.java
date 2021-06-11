package com.bcp.monitoring.controller;

import com.bcp.monitoring.dto.scan.ScanDtoShow;
import com.bcp.monitoring.service.ScanSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class ScanController {

    @Autowired
    public ScanSerive scanSerive;

    @GetMapping("/scans")
    public ResponseEntity<List<ScanDtoShow>> getAllScans(){
        List<ScanDtoShow> scanDtoShows = scanSerive.getAllScans();
        return new ResponseEntity<>(scanDtoShows, HttpStatus.OK);
    }

    @PostMapping("/test/{id}/scan")
    public ResponseEntity<List<ScanDtoShow>> scanTest(@PathVariable(name = "id") Long id){
        List<ScanDtoShow> scanDtoShows = scanSerive.scanTest(id);
        return new ResponseEntity<>(scanDtoShows, HttpStatus.OK);
    }
}
