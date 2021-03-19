package com.bcp.monitoring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "equipes")
public @Data class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
