package com.bcp.monitoring.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "anomalies")
public @Data class Anomalie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String erreur;
    private String date;
    private boolean fixed;


    public Anomalie(){
        // default constructor
    }



}
