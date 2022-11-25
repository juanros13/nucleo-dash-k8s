package com.nucleo.dash.msvc.gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private Environment env;

    @GetMapping("/test")
    public Map<String, Object> listar() {
        Map<String, Object> body = new HashMap<>();
        body.put("texto", env.getProperty("config.texto") );
        //return Collections.singletonMap("usuarios", service.listar());
        return body;
    }

}
