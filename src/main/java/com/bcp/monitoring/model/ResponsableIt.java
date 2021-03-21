package com.bcp.monitoring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "responsable_it")
public @Data class ResponsableIt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
