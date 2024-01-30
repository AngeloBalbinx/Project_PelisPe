package com.example.peliculas.controllers;

import com.example.peliculas.entities.Pelicula;
import com.example.peliculas.service.GeneroService;
import com.example.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("peliculas")
@Controller
public class PeliculaController {
    @Autowired
    PeliculaService peliculaService;
    @Autowired
    GeneroService generoService;
    @GetMapping
    public String crear(Model model){
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("generos",generoService.findAll());
        model.addAttribute("titulo","Nueva Película");
        return "pelicula";
    }
    @GetMapping("{id}")
    public String editar(@PathVariable Long id, Model model){
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("generos",generoService.findAll());
        model.addAttribute("titulo","Nueva Película");
        return "pelicula";
    }
    @PostMapping
    public String guardar(Pelicula pelicula){
        peliculaService.save(pelicula);
        return "redirect:peliculas/home";
    }
    @GetMapping({"/","/home","/index"})
        public String home(){
        return "home";
    }

}
