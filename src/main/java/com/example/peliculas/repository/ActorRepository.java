package com.example.peliculas.repository;

import com.example.peliculas.entities.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor,Long> {
}
