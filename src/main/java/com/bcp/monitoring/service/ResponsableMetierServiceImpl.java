package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.ResponsableMetierConvertor;
import com.bcp.monitoring.dto.ResponsableMetierDto;
import com.bcp.monitoring.model.ResponsableMetier;
import com.bcp.monitoring.repository.ResponsableMetierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsableMetierServiceImpl implements ResponsableMetierService{


    @Autowired
    public ResponsableMetierConvertor responsableMetierConvertor;

    @Autowired
    public ResponsableMetierRepository responsableMetierRepository;

    @Transactional
    @Override
    public ResponsableMetierDto createResponsableMetier(ResponsableMetierDto responsableMetierDto) {
        ResponsableMetier responsableMetier = new ResponsableMetier();
        responsableMetierConvertor.dtoToEntity(responsableMetierDto,responsableMetier);
        ResponsableMetier savedResponsableMetier = responsableMetierRepository.save(responsableMetier);
        return responsableMetierConvertor.entityToDto(savedResponsableMetier);
    }
    @Transactional
    @Override
    public ResponsableMetierDto updateResponsableMetier(Long id, ResponsableMetierDto responsableMetierDto) {
        Optional<ResponsableMetier> responsableMetier = responsableMetierRepository.findById(id);
        if (responsableMetier.isPresent()){
            responsableMetierConvertor.dtoToEntity(responsableMetierDto,responsableMetier.get());
            ResponsableMetier updatedResponsableMetier = responsableMetierRepository.save(responsableMetier.get());
            return responsableMetierConvertor.entityToDto(updatedResponsableMetier);
        }
        return null;
    }

    @Override
    public List<ResponsableMetierDto> getAllResponsableMetiersIt() {
        List<ResponsableMetier> responsableMetiers = responsableMetierRepository.findAllByOrderByIdDesc();
        return responsableMetierConvertor.entitiesToDtos(responsableMetiers);
    }

    @Override
    public String deleteResponsableMetier(Long id) {
        Optional<ResponsableMetier> responsableMetier = responsableMetierRepository.findById(id);
        if (responsableMetier.isPresent()) {
            responsableMetierRepository.deleteById(responsableMetier.get().getId());
            return "Responsable Metier with id: " + id + " deleted successfully!";
        }
        return null;
    }
}
