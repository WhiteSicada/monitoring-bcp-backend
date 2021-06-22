package com.bcp.monitoring.service;

import com.bcp.monitoring.dto.anomalie.AnomalieDto;
import com.bcp.monitoring.model.Anomalie;

public interface AnomalieService {

    AnomalieDto getAllAnomalies(Long id);
}
