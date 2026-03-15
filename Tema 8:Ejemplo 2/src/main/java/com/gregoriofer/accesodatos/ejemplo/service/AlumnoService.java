package com.gregoriofer.accesodatos.ejemplo.service;

import com.gregoriofer.accesodatos.ejemplo.dto.AlumnoDto;
import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeActualizacionDeAlumno;
import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeCreacionDeAlumno;

import java.util.List;

public interface AlumnoService {

    AlumnoDto grabar(final PeticionDeCreacionDeAlumno alumno);

    AlumnoDto actualizar(final String codigo, final PeticionDeCreacionDeAlumno alumno);

    AlumnoDto actualizar(final PeticionDeActualizacionDeAlumno alumno);

    List<AlumnoDto> buscar(final String codigo, final String nombre);

    List<AlumnoDto> listar();

    void eliminar(final String codigo);
}
