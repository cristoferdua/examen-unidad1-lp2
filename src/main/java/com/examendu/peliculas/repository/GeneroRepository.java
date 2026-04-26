package com.examendu.peliculas.repository;

import com.examendu.peliculas.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GeneroRepository extends JpaRepository<Genero, UUID> {
}
