package com.bcp.monitoring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "responsable_metier")
public @Data class ResponsableMetier{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

}
