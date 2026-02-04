package com.gregoriofer.accesodatos.ejemplo.service;

import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeCreacionDeAlumno;
import org.springframework.stereotype.Service;

@Service("servicioAlumnos")
public class AlumnoServiceImpl implements AlumnoService {

    @Override
    public String grabarAlumno(final PeticionDeCreacionDeAlumno data) {
        return "Alta del alumno: Nombre " + data.getNombre() + " y Apellidos " + data.getApellidos();
    }
}
