package com.bcp.monitoring.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projets")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "responsable_it_id", referencedColumnName = "id")
    private ResponsableIt responsableIt;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "responsable_metier_id", referencedColumnName = "id")
    private ResponsableMetier responsableMetier;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "equipe_id", referencedColumnName = "id")
    private Equipe equipe;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "projets_apis" ,joinColumns = {@JoinColumn(name = "projet_id")},inverseJoinColumns = {@JoinColumn(name = "api_id")})
    private Set<Api> apis;

    // if we want to add an API to a Project
    public void addAPI(Api api){
        this.apis.add(api);
    }

    // if we want to remove an API from a project
    public void removeAPI(Api api){
        this.apis.remove(api);
    }


}
