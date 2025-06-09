package com.rick.Bookland.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libros, Long> {
    Optional<Libros> findByTituloIgnoreCase(String titulo);

    List<Libros> findAll();

    List <Libros> findByidiomas(String idioma);

    List <Libros> findTop5ByOrderByDescargasDesc();

}
