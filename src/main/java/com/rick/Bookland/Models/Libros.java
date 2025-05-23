package com.rick.Bookland.Models;

import java.util.List;
import java.util.Objects;

public class Libros {
    private int numero;
    private String titulo;
    private List<String> tema;
    private List<Persons>  autores;
    private List<String>  resumen;
    private List<String>  idiomas;
    private Double descargas;

    public Libros(Books b) {
        this.numero = b.numero();
        this.titulo = b.titulo();
        this.tema = b.tema();
        this.autores = b.autores();
        this.resumen = b.resumen();
        this.idiomas = b.idiomas();
        this.descargas = b.descargas();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getTema() {
        return tema;
    }

    public void setTema(List<String> tema) {
        this.tema = tema;
    }

    public List<Persons> getAutores() {
        return autores;
    }

    public void setAutores(List<Persons> autores) {
        this.autores = autores;
    }

    public List<String> getResumen() {
        return resumen;
    }

    public void setResumen(List<String> resumen) {
        this.resumen = resumen;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
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
        return numero == libros.numero && descargas == libros.descargas && Objects.equals(titulo, libros.titulo) && Objects.equals(tema, libros.tema) && Objects.equals(autores, libros.autores) && Objects.equals(resumen, libros.resumen) && Objects.equals(idiomas, libros.idiomas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, titulo, tema, autores, resumen, idiomas, descargas);
    }

    @Override
    public String toString() {
        return
                "descargas: " + descargas +
                ", titulo:'" + titulo + '\''
                ;
    }
}
