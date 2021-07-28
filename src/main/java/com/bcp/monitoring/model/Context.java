package com.bcp.monitoring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "context")
public @Data
class Context {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="context_id", nullable=false)
    // list of endpoints
    private List<Endpoint> endpoints;

    // constructor
    public Context() {
    }



    public Context(String name, List<Endpoint> endpoints) {
        this.name = name;
        this.endpoints = endpoints;
    }

    // add an endpoint of the list of context endpoints
    public void addEndpoint(Endpoint endpoint){
        this.getEndpoints().add(endpoint);
    }

    // remove an endpoint from the list of context endpoints
    public void removeEndpoint(Endpoint endpoint){
        this.getEndpoints().remove(endpoint);
    }

    // update an endpoint in the list of context endpoints
    public void updateEndpoint(Endpoint oldEndpoint, Endpoint newEndpoint){
        int index = this.getEndpoints().indexOf(oldEndpoint);
        this.getEndpoints().set(index, newEndpoint);
    }
}
