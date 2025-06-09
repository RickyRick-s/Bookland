package com.rick.Bookland.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private int nacimiento;
    private int fallecimiento;
    @ManyToMany(mappedBy = "autores", fetch= FetchType.EAGER)
    private List<Libros> libros;

    public Autores(){}

    public Autores(String nombre, int nacimiento, int fallecimiento) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.fallecimiento = fallecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(int fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    @Override
    public String toString() {
        return
                "########################"+"\n"+
                "nombre: " + nombre + '\n' +
                "nacimiento: " + nacimiento + '\n' +
                "fallecimiento: " + fallecimiento +'\n'+
                "#########################"+"\n";
    }
}
