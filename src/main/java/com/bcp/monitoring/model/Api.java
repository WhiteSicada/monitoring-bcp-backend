package com.bcp.monitoring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "apis")
public @Data
class Api {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String ip;
    private int port;
    @Lob
    private String token;
    private boolean status = false;
    private boolean db = false;
    private boolean diskspace = false;
    private boolean ping = false;

    // any action performed on api will be on endpoints, and if we remove api all his endpoint will be removed
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="api_id", nullable=false)
    private List<Context> contexts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="api_id", nullable=false)
    private List<Anomalie> anomalies = new ArrayList<>();

    public Api() {
    }

    public void addAnomalie(Anomalie anomalie){
        this.getAnomalies().add(anomalie);
    }


    public void addContext(Context context){
        this.getContexts().add(context);
    }

    public void removeContext(Context context){
        this.getContexts().remove(context);
    }

    public void updateContext(int index, Context newContext){
        this.getContexts().set(index, newContext);
    }

}