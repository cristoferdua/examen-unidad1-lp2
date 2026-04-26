package com.examendu.peliculas.repository;

import com.examendu.peliculas.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
}
