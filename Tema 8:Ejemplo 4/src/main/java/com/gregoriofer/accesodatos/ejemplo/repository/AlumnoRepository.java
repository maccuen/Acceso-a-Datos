package com.gregoriofer.accesodatos.ejemplo.repository;

import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeBusquedaDeAlumno;
import com.gregoriofer.accesodatos.ejemplo.model.Alumno;

import java.util.List;

public interface AlumnoRepository {

    List<Alumno> buscar(PeticionDeBusquedaDeAlumno filtro);
}
