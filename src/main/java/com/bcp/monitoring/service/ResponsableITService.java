package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.ResponsableItDto;
import com.bcp.monitoring.model.ResponsableIt;

import java.util.List;

public interface ResponsableITService {

    ResponsableItDto createResponsableIt(ResponsableItDto responsableItDto);

    ResponsableItDto updateResponsableIt(Long id,ResponsableItDto responsableItDto);

    List<ResponsableItDto> getAllResponsablesIt();

    String deleteResponsibleIt(Long id);

}
