package com.gregoriofer.accesodatos.ejemplo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeticionDeBusquedaDeAlumno {

    private String codigo;
    private String nombre;

    private String apellidos;

    private String dni;

    private String ciclo;

    private Integer curso;

    private Integer cursoMinimo;
    private Integer cursoMaximo;
}
