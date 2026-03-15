package com.gregoriofer.accesodatos.ejemplo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CICLO")
@Getter
@Setter
public class Ciclo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, updatable = false, unique = true)
    private String codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;
}
