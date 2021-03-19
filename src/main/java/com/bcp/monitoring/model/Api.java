package com.bcp.monitoring.model;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "apis")
public @Data class Api {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String url;
    private boolean status = false;
    private boolean db = false;
    private boolean diskspace = false;
    private boolean ping = false;

    @OneToMany(mappedBy = "api")
    private List<Anomalie> anomalies;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,mappedBy = "listAPIs")
    private Set<Projet> listProjets;

    public void removeApiFromProject(Projet projet){
        this.getListProjets().remove(projet);
        projet.getListAPIs().remove(this);
    }

    public void removeApiFromProjects(){
        for (Projet projet : new HashSet<>(listProjets)){
            removeApiFromProject(projet);
        }
    }

    public void addApiToProject(Projet projet){
        this.getListProjets().add(projet);
        projet.getListAPIs().add(this);
    }

    public void addApiToListProject(){
        for (Projet projet : new HashSet<>(listProjets)){
            addApiToProject(projet);
        }
    }

}