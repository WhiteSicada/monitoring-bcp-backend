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

    private String error;
    private String endpoint;
    private String date;
    private boolean fixed = false;




    public Anomalie(){
        // default constructor
    }

    public Anomalie(String error, String endpoint, String date) {
        this.error = error;
        this.endpoint = endpoint;
        this.date = date;
    }
}
