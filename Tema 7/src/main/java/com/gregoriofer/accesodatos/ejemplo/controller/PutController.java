package com.gregoriofer.accesodatos.ejemplo.controller;

import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeActualizacionDeAlumno;
import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeCreacionDeAlumno;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ejemplo_put")
public class PutController {

    @PutMapping("/actualizarAlumno/{codigo}")
    public String actualizar(@PathVariable final String codigo,
                             @Valid @RequestBody final PeticionDeCreacionDeAlumno data) {
        return "Actualización del alumno código " + codigo + ", Nombre " + data.getNombre()
                + " y Apellidos " + data.getApellidos();
    }

    @PutMapping(value = "/actualizarAlumno2", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PeticionDeActualizacionDeAlumno actualizar2(@Valid @RequestBody final PeticionDeActualizacionDeAlumno data) {
        return data;
    }
}
