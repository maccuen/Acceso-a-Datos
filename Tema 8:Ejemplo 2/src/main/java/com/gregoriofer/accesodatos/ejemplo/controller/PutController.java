package com.gregoriofer.accesodatos.ejemplo.controller;

import com.gregoriofer.accesodatos.ejemplo.dto.AlumnoDto;
import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeActualizacionDeAlumno;
import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeCreacionDeAlumno;
import com.gregoriofer.accesodatos.ejemplo.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ejemplo_put")
public class PutController {

    private final AlumnoService alumnoService;

    public PutController(final AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PutMapping("/actualizarAlumno/{codigo}")
    public String actualizar(@PathVariable final String codigo,
                             @Valid @RequestBody final PeticionDeCreacionDeAlumno data) {
        final AlumnoDto alumno = alumnoService.actualizar(codigo, data);
        return "Actualización del alumno código " + alumno.getCodigo() + ", Nombre " + alumno.getNombre()
                + " y Apellidos " + alumno.getApellidos();
    }

    @PutMapping(value = "/actualizarAlumno2", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AlumnoDto actualizar2(@Valid @RequestBody final PeticionDeActualizacionDeAlumno data) {
        return alumnoService.actualizar(data);
    }
}
