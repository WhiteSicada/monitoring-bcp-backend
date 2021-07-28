package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.contexts.LitsContextsNames;
import com.bcp.monitoring.model.Context;


public interface ContextService {

    Context updateContext(Long id,Context newContext);
    String deleteContext(Long id);
}
