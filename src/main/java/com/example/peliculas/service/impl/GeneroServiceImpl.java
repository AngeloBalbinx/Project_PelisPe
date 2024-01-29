package com.example.peliculas.service.impl;

import com.example.peliculas.entities.Genero;
import com.example.peliculas.repository.GeneroRepository;
import com.example.peliculas.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService {
    @Autowired
    GeneroRepository repoGen;
    @Override
    public Genero addGenero(Genero genero) {
        return repoGen.save(genero);
    }

    @Override
    public Optional<Genero> getById(Long id) {
        return repoGen.findById(id);

    }

    @Override
    public void delete(Long id) {
        repoGen.deleteById(id);
    }

    @Override
    public List<Genero> findAll() {
        return (List<Genero>) repoGen.findAll();
    }
}
