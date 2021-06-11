package com.bcp.monitoring.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "scan")
public class Scan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "api_id" , referencedColumnName = "id")
    private Api api;

    @OneToOne
    @JoinColumn(name = "test_id" , referencedColumnName = "id")
    private Test test;

    @OneToOne
    @JoinColumn(name = "endpoint_id" , referencedColumnName = "id")
    private Endpoint endpoint;

    private String status;
    private String spark;
    private String creates_at;

    public Scan() {
    }


}
