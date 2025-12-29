package com.gregoriofer.accesodatos.ejemplo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ejemplo_delete")
public class DeleteController {

    @DeleteMapping("/bajaAlumno/{codigo}")
    public String baja(@PathVariable final String codigo) {
        return "adios " + codigo;
    }
}
