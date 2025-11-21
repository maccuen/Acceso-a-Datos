package com.gregoriofer.accesodatos.ejemplo.controller;

import com.gregoriofer.accesodatos.ejemplo.config.PropertiesClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final PropertiesClass propertiesClass;

    public TestController(final PropertiesClass propertiesClass) {
        this.propertiesClass = propertiesClass;
    }

    @GetMapping("/hola")
    public String test() {
        return "adios " + propertiesClass.getTitulo();
    }
}
