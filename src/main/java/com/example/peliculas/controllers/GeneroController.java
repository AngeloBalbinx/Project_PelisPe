package com.example.peliculas.controllers;


import com.example.peliculas.entities.Genero;
import com.example.peliculas.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RequestMapping("generos")
@RestController
public class GeneroController {
    @Autowired
    private GeneroService generoService;

    @PostMapping
    public Long guardar(@RequestParam String nombre){
        Genero genero = new Genero();
        genero.setNombre(nombre);
        generoService.addGenero(genero);
        return genero.getId();
    }
    @GetMapping("{id}")
    @ResponseBody
    public Genero buscarPorId(@PathVariable Long id) {
        return generoService.getById(id).orElse(null);
    }

}
