package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.ResponsableItConvertor;
import com.bcp.monitoring.dto.ResponsableItDto;
import com.bcp.monitoring.model.ResponsableIt;
import com.bcp.monitoring.repository.ResponsableItRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsableITServiceImpl implements ResponsableITService {

    @Autowired
    public ResponsableItConvertor responsableItConvertor;

    @Autowired
    public ResponsableItRepository responsableItRepository;

    @Transactional
    @Override
    public ResponsableItDto createResponsableIt(ResponsableItDto responsableItDto) {
        ResponsableIt responsableIt = new ResponsableIt();
        responsableItConvertor.dtoToEntity(responsableItDto,responsableIt);
        ResponsableIt savedResponsableIt = responsableItRepository.save(responsableIt);
        return responsableItConvertor.entityToDto(savedResponsableIt);
    }

    @Transactional
    @Override
    public ResponsableItDto updateResponsableIt(Long id, ResponsableItDto responsableItDto) {
        Optional<ResponsableIt> responsableIt = responsableItRepository.findById(id);
        if (responsableIt.isPresent()){
            responsableItConvertor.dtoToEntity(responsableItDto,responsableIt.get());
            ResponsableIt updatedResponsableIt = responsableItRepository.save(responsableIt.get());
            return responsableItConvertor.entityToDto(updatedResponsableIt);
        }
        return null;
    }

    @Override
    public List<ResponsableItDto> getAllResponsablesIt() {
        List<ResponsableIt> responsableItList = responsableItRepository.findAllByOrderByIdDesc();
        return responsableItConvertor.entitiesToDtos(responsableItList);
    }

    @Override
    public String deleteResponsibleIt(Long id) {
        Optional<ResponsableIt> responsableIt = responsableItRepository.findById(id);
        if (responsableIt.isPresent()) {
            responsableItRepository.deleteById(responsableIt.get().getId());
            return "Responsable It with id: " + id + " deleted successfully!";
        }
        return null;
    }
}
