package com.gregoriofer.accesodatos.ejemplo.controller;

import com.gregoriofer.accesodatos.ejemplo.service.AlumnoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ejemplo_delete")
public class DeleteController {

    private final AlumnoService alumnoService;

    public DeleteController(final AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @DeleteMapping("/bajaAlumno/{codigo}")
    public String baja(@PathVariable final String codigo) {
        alumnoService.eliminar(codigo);
        return "adios " + codigo;
    }
}
