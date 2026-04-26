package com.examendu.peliculas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "pelicula_generos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pelicula_id", "genero_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaGenero {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;
}
