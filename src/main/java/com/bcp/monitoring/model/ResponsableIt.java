package com.bcp.monitoring.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "responsable_it")
public @Data class ResponsableIt extends Responsable{
}
