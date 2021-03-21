package com.bcp.monitoring.model;


import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projets")
public @Data class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsable_it_id", referencedColumnName = "id")
    private ResponsableIt responsableIt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsable_metier_id", referencedColumnName = "id")
    private ResponsableMetier responsableMetier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipe_id", referencedColumnName = "id")
    private Equipe equipe;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "projets_apis" ,joinColumns = {@JoinColumn(name = "projet_id")},inverseJoinColumns = {@JoinColumn(name = "api_id")})
    private Set<Api> listAPIs;


    // if we want to add an API to a Project
    public void addAPI(Api api){
        // add API to the list of APIs of the current project
        this.listAPIs.add(api);
        // add the current  project to the list of project of the API
        api.getListProjets().add(this);
    }


    // if we want to add list API to a Project
    public void addListApi(){
        // for each API in the list of APIs of the current project
        for (Api api : new HashSet<>(listAPIs)){
            // create the relations between the project and the APIS
            addAPI(api);
        }
    }


    // if we want to remove an API from a project
    public void removeAPI(Api api){
        // remove API from the list of APIs of the current project
        this.listAPIs.remove(api);
        // remove the current  project from the list of project of the API
        api.getListProjets().remove(this);
    }

    // if we want to remove all the relational connections betwwen the project and the APIS
    public void removeAPIs(){
        // for each API in the list of APIs of the current project
        for (Api api : new HashSet<>(listAPIs)){
            // disAssociate the relations between the project and the APIS
            removeAPI(api);
        }
    }
}
