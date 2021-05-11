package com.bcp.monitoring.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "apis")
public class Api {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String ip;
    private int port;
    private boolean status = false;
    private boolean db = false;
    private boolean diskspace = false;
    private boolean ping = false;

    // any action performed on api will be on endpoints, and if we remove api all his endpoint will be removed
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="api_id", nullable=false)
    private List<Endpoint> endpoints;

    @OneToMany(mappedBy = "api")
    private List<Anomalie> anomalies;

    public Api() {
    }

    public void addEndpoint(Endpoint endpoint){
        this.getEndpoints().add(endpoint);
    }

    public void removeEndpoint(Endpoint endpoint){
        this.getEndpoints().remove(endpoint);
    }
}