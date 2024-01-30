package com.example.peliculas.controllers;

import com.example.peliculas.entities.Actor;
import com.example.peliculas.entities.Pelicula;
import com.example.peliculas.service.ActorService;
import com.example.peliculas.service.GeneroService;
import com.example.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("peliculas")
@Controller
public class PeliculaController {
    @Autowired
    PeliculaService peliculaService;
    @Autowired
    GeneroService generoService;
    @Autowired
    ActorService actorService;
    @GetMapping
    public String crear(Model model){
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("generos",generoService.findAll());
        model.addAttribute("actores",actorService.findAll());
        model.addAttribute("titulo","Nueva Película");
        return "pelicula";
    }
    @GetMapping("{id}")
    public String editar(@PathVariable Long id, Model model){
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("generos",generoService.findAll());
        model.addAttribute("actores",actorService.findAll());
        model.addAttribute("titulo","Editar Película");
        return "pelicula";
    }
    @PostMapping
    public String guardar(Pelicula pelicula, @ModelAttribute String ids){
        List<Long> idsProtagonistas = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        List<Actor> protagonistas = actorService.findAllById(idsProtagonistas);
        pelicula.setProtagonistas(protagonistas);
        peliculaService.save(pelicula);
        return "redirect:peliculas/home";
    }
    @GetMapping({"/","/home","/index"})
        public String home(){
        return "home";
    }

}
