package com.bcp.monitoring.controller;
import com.bcp.monitoring.dto.api.ListApisDto;
import com.bcp.monitoring.dto.projet.ProjetDto;
import com.bcp.monitoring.dto.projet.ProjetDtoShow;
import com.bcp.monitoring.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProjetController {

    @Autowired
    public ProjetService projetService;

    @PostMapping("/projet")
    public ResponseEntity<?> createProject(@RequestBody ProjetDto projetDto) {
        ProjetDtoShow projet = projetService.createProjet(projetDto);
        if (projet == null){
            return new ResponseEntity<>("cannot create Projet",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projet, HttpStatus.CREATED);
    }

    @GetMapping("/projets")
    public ResponseEntity<List<ProjetDtoShow>> getAllProjets(){
        List<ProjetDtoShow> projetDtoList = projetService.getAllProjets();
        return new ResponseEntity<>(projetDtoList,HttpStatus.OK);
    }

    @DeleteMapping("/projet/{id}")
    public ResponseEntity<String> deleteProjet(@PathVariable(name = "id") Long id){
        String message = projetService.deleteProjet(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PutMapping("/projet/{id}")
    public ResponseEntity<?> updateProjet(@PathVariable(name = "id") Long id, @RequestBody ProjetDto projetDto){
        ProjetDtoShow updatedProjet = projetService.updateProjet(id, projetDto);
        if (updatedProjet == null){
            return new ResponseEntity<>("cannot update Projet",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedProjet,HttpStatus.OK);
    }

    @PutMapping("/projet/{id}/addApis")
    public ResponseEntity<?> addApiToProjet(@PathVariable(name = "id") Long id, @RequestBody ListApisDto apis){
        ProjetDtoShow updatedProjet = projetService.addApisToProjet(id,apis);
        if (updatedProjet == null){
            return new ResponseEntity<>("cannot update Projet",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedProjet,HttpStatus.OK);
    }

    @PutMapping("/projet/{id}/removeApis")
    public ResponseEntity<?> removeApiFromProjet(@PathVariable(name = "id") Long id, @RequestBody ListApisDto apis){
        ProjetDtoShow updatedProjet = projetService.removeApisFromProjet(id,apis);
        if (updatedProjet == null){
            return new ResponseEntity<>("cannot update Projet",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedProjet,HttpStatus.OK);
    }
}
