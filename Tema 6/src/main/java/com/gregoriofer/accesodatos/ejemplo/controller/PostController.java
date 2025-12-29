package com.gregoriofer.accesodatos.ejemplo.controller;

import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeCreacionDeAlumno;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/ejemplo_post")
public class PostController {

    @PostMapping("/altaAlumno")
    public String crear(@Valid @RequestBody final PeticionDeCreacionDeAlumno data) {
        return "Alta del alumno: Nombre " + data.getNombre() + " y Apellidos " + data.getApellidos();
    }

    @PostMapping(value="/altaAlumno2", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PeticionDeCreacionDeAlumno crear2(@Valid @RequestBody final PeticionDeCreacionDeAlumno data) {
        return data;
    }
}
