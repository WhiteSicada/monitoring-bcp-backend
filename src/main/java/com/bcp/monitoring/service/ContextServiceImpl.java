package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.ContextConvertor;
import com.bcp.monitoring.dto.contexts.LitsContextsNames;
import com.bcp.monitoring.model.Context;
import com.bcp.monitoring.repository.ContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContextServiceImpl implements ContextService {
    @Autowired
    public ContextRepository contextRepository;

    @Autowired
    public ContextConvertor contextConvertor;



    @Override
    public Context updateContext(Long id, Context newContext) {
        Optional<Context> oldContext = contextRepository.findById(id);
        if(oldContext.isPresent()){
            oldContext.get().setName(newContext.getName());
//            testConvertor.dtoToEntity(testDto,test.get());
            return contextRepository.save(oldContext.get());
        }
        return null;
    }

    @Override
    public String deleteContext(Long id) {
        Optional<Context> oldContext = contextRepository.findById(id);
        if(oldContext.isPresent()){
            contextRepository.deleteById(oldContext.get().getId());
            return "Context with id: " + id + " deleted successfully!";
        }
        return null;
    }
}
