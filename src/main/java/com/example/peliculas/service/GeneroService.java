package com.example.peliculas.service;

import com.example.peliculas.entities.Genero;

import java.util.List;
import java.util.Optional;

public interface GeneroService {
    public Genero addGenero(Genero genero);
    public Optional<Genero> getById(Long id);
    public void delete(Long id);
    public List<Genero> findAll();

}
