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
    private String successful;
    private String execution_time;
    private String creates_at;
    private String method;
    private String url;

    public Scan() {
    }

    public Scan(Api api, Test test, Endpoint endpoint, String status, String spark, String successful, String execution_time, String creates_at, String method, String url) {
        this.api = api;
        this.test = test;
        this.endpoint = endpoint;
        this.status = status;
        this.spark = spark;
        this.successful = successful;
        this.execution_time = execution_time;
        this.creates_at = creates_at;
        this.method = method;
        this.url = url;
    }
}
