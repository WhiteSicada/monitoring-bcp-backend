package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.ResponsableMetierDto;

import java.util.List;

public interface ResponsableMetierService {

    ResponsableMetierDto createResponsableMetier(ResponsableMetierDto responsableMetierDto);

    ResponsableMetierDto updateResponsableMetier(Long id,ResponsableMetierDto responsableMetierDto);

    List<ResponsableMetierDto> getAllResponsableMetiersIt();

    String deleteResponsableMetier(Long id);
}
