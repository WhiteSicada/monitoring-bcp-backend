package com.bcp.monitoring.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "responsable_metier")
public @Data class ResponsableMetier extends Responsable{
}
