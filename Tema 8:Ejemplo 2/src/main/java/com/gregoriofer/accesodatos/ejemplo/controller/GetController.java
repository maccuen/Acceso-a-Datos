package com.gregoriofer.accesodatos.ejemplo.controller;

import com.gregoriofer.accesodatos.ejemplo.dto.AlumnoDto;
import com.gregoriofer.accesodatos.ejemplo.service.AlumnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejemplo_get")
public class GetController {

    private final AlumnoService alumnoService;

    public GetController(final AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("/listar")
    public List<AlumnoDto> listar() {
        return alumnoService.listar();
    }

    @GetMapping("/buscarAlumno")
    public List<AlumnoDto> buscar(@RequestParam(name = "codigo", required = true) final String codigo,
                                  @RequestParam(name = "nombre", required = false) final String nombre) {
        return alumnoService.buscar(codigo, nombre);
    }

    @GetMapping("/buscarAlumno2/{codigo}/{nombre}")
    public List<AlumnoDto> buscar2(@PathVariable final String codigo, @PathVariable final String nombre) {
        return alumnoService.buscar(codigo, nombre);
    }
}

