package com.bcp.monitoring.configuration.actuatorDeserialisation;

import com.bcp.monitoring.configuration.actuatorDeserialisation.db.Db;
import com.bcp.monitoring.configuration.actuatorDeserialisation.diskspace.Diskspace;
import com.bcp.monitoring.configuration.actuatorDeserialisation.ping.Ping;
import lombok.Data;

public @Data class Component {
    private Db db;
    private Diskspace diskSpace;
    private Ping ping;
}
