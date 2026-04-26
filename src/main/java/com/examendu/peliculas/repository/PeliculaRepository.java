package com.examendu.peliculas.repository;

import com.examendu.peliculas.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PeliculaRepository extends JpaRepository<Pelicula, UUID> {
}
