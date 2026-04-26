package com.examendu.peliculas.repository;

import com.examendu.peliculas.entity.PeliculaGenero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PeliculaGeneroRepository extends JpaRepository<PeliculaGenero, UUID> {
}
