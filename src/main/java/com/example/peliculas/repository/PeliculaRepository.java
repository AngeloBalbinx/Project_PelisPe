package com.example.peliculas.repository;

import com.example.peliculas.entities.Pelicula;
import org.springframework.data.repository.CrudRepository;

public interface PeliculaRepository extends CrudRepository<Pelicula,Long> {

}
