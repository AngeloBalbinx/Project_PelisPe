package com.example.peliculas.service.impl;

import com.example.peliculas.entities.Pelicula;
import com.example.peliculas.repository.PeliculaRepository;
import com.example.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PeliculaServiceImpl implements PeliculaService {
    @Autowired
    private PeliculaRepository repoPeli;
    @Override
    public void save(Pelicula pelicula) {
        repoPeli.save(pelicula);
    }

    @Override
    public Pelicula findById(Long id) {
        return repoPeli.findById(id).orElse(null);
    }

    @Override
    public List<Pelicula> findAll() {
        return (List<Pelicula>) repoPeli.findAll();
    }

    @Override
    public void delete(Long id) {
        repoPeli.deleteById(id);
    }
}
