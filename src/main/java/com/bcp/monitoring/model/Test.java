package com.bcp.monitoring.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long interval;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "test_apis" ,joinColumns = {@JoinColumn(name = "test_id")},inverseJoinColumns = {@JoinColumn(name = "api_id")})
    private List<Api> listAPIs;

    public void addAPI(Api api){
        this.listAPIs.add(api);
    }

    // if we want to remove an API from a project
    public void removeAPI(Api api){
        this.listAPIs.remove(api);
    }
}
