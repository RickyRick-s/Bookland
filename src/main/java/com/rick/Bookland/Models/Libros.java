package com.rick.Bookland.Models;
import jakarta.persistence.*;
import java.util.stream.*;
import java.util.List;
import java.util.Objects;

@Entity

public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(unique = true)
    private String titulo;
    @Column(length = 1000)
    private String tema;
    @ManyToMany(cascade = CascadeType.MERGE,fetch= FetchType.EAGER)
    @JoinTable(name = "libros_autores",
    joinColumns = @JoinColumn(name= "libro_id"),
    inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autores>  autores;
    private String idiomas;
    private Double descargas;

    public Libros(){}

    public Libros(Books b) {
        this.titulo = b.titulo();
        this.tema = String.join(", ", b.tema());;
        this.autores = b.autores().stream()
                .map(a -> new Autores(a.nombre(), a.nacimiento(), a.fallecimiento()))
                .toList();
        this.idiomas = String.join(", ", b.idiomas());
        this.descargas = b.descargas();
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public void setAutores(List<Autores> autores) {
        this.autores = autores;
    }


    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libros libros = (Libros) o;
        return Id == libros.Id && Objects.equals(titulo, libros.titulo) && Objects.equals(tema, libros.tema) && Objects.equals(autores, libros.autores) && Objects.equals(idiomas, libros.idiomas) && Objects.equals(descargas, libros.descargas);
    }

    @Override
    public String toString() {
        return
            "########################"+"\n"+
            "Titulo: " + titulo + "\n" +
            "Autores: " + autores.stream().map(Autores::getNombre).collect(Collectors.joining(", ")) + "\n" +
            "Idioma: " + idiomas + "\n" +
            "Descargas: " + descargas + "\n"+
             "#########################"+"\n";

    }
}
