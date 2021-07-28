package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.EquipeConvertor;
import com.bcp.monitoring.dto.EquipeDto;
import com.bcp.monitoring.model.Equipe;
import com.bcp.monitoring.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EquipeServiceImpl implements EquipeService{

    @Autowired
    public EquipeConvertor equipeConvertor;

    @Autowired
    public EquipeRepository equipeRepository;

    @Transactional
    @Override
    public EquipeDto createEquipe(EquipeDto equipeDto) {
        Equipe equipe = new Equipe();
        equipeConvertor.dtoToEntity(equipeDto, equipe);
        Equipe savedEquipe = equipeRepository.save(equipe);
        return equipeConvertor.entityToDto(savedEquipe);
    }

    @Transactional
    @Override
    public EquipeDto updateEquipe(Long id, EquipeDto equipeDto) {
        Optional<Equipe> equipe = equipeRepository.findById(id);
        if (equipe.isPresent()){
            equipeConvertor.dtoToEntity(equipeDto,equipe.get());
            Equipe updatedEquipe = equipeRepository.save(equipe.get());
            return equipeConvertor.entityToDto(updatedEquipe);
        }
        return null;
    }

    @Override
    public String deleteEquipe(Long id) {
        Optional<Equipe> equipe = equipeRepository.findById(id);
        if (equipe.isPresent()){
            equipeRepository.deleteById(equipe.get().getId());
            return "Equipe with id: " + id + " deleted successfully!";
        }
        return "Equipe with id: " + id + " Does not exists";
    }

    @Override
    public List<EquipeDto> getEquipeList() {
        List<Equipe> equipeDtoList = equipeRepository.findAllByOrderByIdDesc();
        return equipeConvertor.entitiesToDtos(equipeDtoList);
    }
}
