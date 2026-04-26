package com.examendu.peliculas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "sinopsis", length = 2000)
    private String sinopsis;

    @Column(name = "anyo_estreno", nullable = false)
    private Integer anyoEstreno;

    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos;

    @Column(name = "clasificacion", length = 20)
    private String clasificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PeliculaGenero> peliculaGeneros = new ArrayList<>();
}
