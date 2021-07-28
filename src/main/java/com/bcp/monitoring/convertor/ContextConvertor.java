package com.bcp.monitoring.convertor;

import com.bcp.monitoring.dto.contexts.ContextUpdateDto;
import com.bcp.monitoring.model.Context;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ContextConvertor {

    public void dtoToEntity(Context oldContext, Context newContext){
        newContext.setId(oldContext.getId());
        newContext.setName(oldContext.getName());
        if(oldContext.getEndpoints() == null){
            newContext.setEndpoints(new ArrayList<>());
        }
    }


}
