package com.gregoriofer.accesodatos.ejemplo.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class PeticionDeCreacionDeAlumno {

    @NotNull(message = "El nombre del alumno es un campo obligatorio.")
    @Size(max = 25, message = "El nombre del alumno tiene una longitud máxima de 25 caracteres.")
    private String nombre;

    @NotNull
    @Size(max = 50)
    private String apellidos;

    @NotNull
    @Size(max = 9)
    private String dni;

    @NotNull
    @Size(max = 3)
    private String ciclo;

    @NotNull
    @Min(1)
    @Max(2)
    private Integer curso;
}
