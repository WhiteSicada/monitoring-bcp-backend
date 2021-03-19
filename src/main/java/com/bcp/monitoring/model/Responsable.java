package com.bcp.monitoring.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Responsable {

    @Id
    private long id;
    private String name;
    private String email;
}
