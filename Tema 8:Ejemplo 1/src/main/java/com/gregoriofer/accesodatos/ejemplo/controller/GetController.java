package com.gregoriofer.accesodatos.ejemplo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ejemplo_get")
public class GetController {

    @GetMapping("/buscarAlumno")
    public String buscar(@RequestParam(name = "codigo", required = true) final String codigo,
                         @RequestParam(name = "nombre", required = false) final String nombre) {
        return "alumno" + codigo + " nombre " + nombre;
    }

    @GetMapping("/buscarAlumno2/{codigo}/{nombre}")
    public String buscar2(@PathVariable final String codigo, @PathVariable final String nombre) {
        return "alumno" + codigo + " nombre " + nombre;
    }
}

