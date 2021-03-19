package com.bcp.monitoring.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "anomalies")
public @Data class Anomalie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String erreur;
    private Instant date;
    private boolean fixed;

    @ManyToOne
    private Api api;

    public Anomalie(){}

}
