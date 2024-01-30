package com.example.peliculas.service.impl;

import com.example.peliculas.entities.Actor;
import com.example.peliculas.repository.ActorRepository;
import com.example.peliculas.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorRepository repoActor;
    @Override
    public List<Actor> findAll() {
        return (List<Actor>) repoActor.findAll();
    }

    @Override
    public List<Actor> findAllById(List<Long> ids) {
        return (List<Actor>) repoActor.findAllById(ids);
    }
}
