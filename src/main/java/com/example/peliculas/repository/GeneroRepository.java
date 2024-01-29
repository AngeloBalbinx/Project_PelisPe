package com.example.peliculas.repository;

import com.example.peliculas.entities.Genero;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GeneroRepository extends CrudRepository<Genero,Long> {
    Optional<Genero> findById(Long id);

}
