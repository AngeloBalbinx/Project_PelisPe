package com.example.peliculas.controllers;

import com.example.peliculas.service.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
@Controller
public class ArchivoController {
    @Autowired
    private ArchivoService archivoService;
    @GetMapping("/archivo")
    public ResponseEntity<Resource> get(@RequestParam("n") String archivo) throws MalformedURLException {
        return archivoService.get(archivo);
    }
}
