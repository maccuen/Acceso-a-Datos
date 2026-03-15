package com.gregoriofer.accesodatos.ejemplo.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "ALUMNO")
@Getter
@Setter
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, updatable = false, unique = true)
    private String codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "ciclo")
    private String ciclo;

    @Column(name = "curso")
    private Integer curso;
}
