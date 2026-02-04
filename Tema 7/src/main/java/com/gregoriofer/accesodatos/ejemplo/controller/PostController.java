package com.gregoriofer.accesodatos.ejemplo.controller;

import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeCreacionDeAlumno;
import com.gregoriofer.accesodatos.ejemplo.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ejemplo_post")
public class PostController {

    private final AlumnoService alumnoService;

    public PostController(@Qualifier("servicioAlumnos") final AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping("/altaAlumno")
    public String crear(@Valid @RequestBody final PeticionDeCreacionDeAlumno data) {
        return alumnoService.grabarAlumno(data);
    }

    @PostMapping(value = "/altaAlumno2", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PeticionDeCreacionDeAlumno crear2(@Valid @RequestBody final PeticionDeCreacionDeAlumno data) {
        return data;
    }
}
