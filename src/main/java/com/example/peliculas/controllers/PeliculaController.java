package com.example.peliculas.controllers;

import com.example.peliculas.entities.Pelicula;
import com.example.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("peliculas")
@Controller
public class PeliculaController {
    @Autowired
    PeliculaService peliculaService;
    @GetMapping
    public String crear(Model model){
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("titulo","Nueva Película");
        return "pelicula";
    }
    @GetMapping("{id}")
    public String editar(@PathVariable Long id, Model model){
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("titulo","Nueva Película");
        return "pelicula";

    }
}
