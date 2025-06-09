package com.rick.Bookland.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutoresRepository extends JpaRepository<Autores, Long> {
    Optional<Autores> findByNombreIgnoreCase(String nombre);

    List<Autores> findAll();

    List<Autores> findBynacimientoBetween(int nacimiento, int fallecimiento);
}
