package com.example.peliculas.service;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;


public interface ArchivoService {
    public void guardar(String archivo, InputStream bytes);
    public void eliminar(String archivo);
    public ResponseEntity<Resource> get(String archivo);
}
