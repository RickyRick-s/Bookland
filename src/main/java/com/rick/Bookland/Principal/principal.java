package com.rick.Bookland.Principal;


import com.rick.Bookland.Models.Datos;
import com.rick.Bookland.Models.Libros;
import com.rick.Bookland.Service.ConvierteDatos;
import com.rick.Bookland.Service.UsoAPI;


import java.util.*;
import java.util.stream.Collectors;

public class principal {
    private Scanner scan = new Scanner(System.in);
    private UsoAPI API = new UsoAPI();
    private final String URL = "https://gutendex.com/books/";

    public void IniciarUI(){
        var json = API.obtenerDatos(URL);
        ConvierteDatos conversor = new ConvierteDatos();
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println("**************************");
        System.out.println("Todos los libros: ");
        System.out.println(datos);
        List<Libros> LibrosObjeto = datos.books().stream()
                .map(b -> new Libros(b))
                .collect(Collectors.toUnmodifiableList());
        System.out.println("**************************");
        System.out.println("Top 10 libros mas descargados");
        LibrosObjeto.stream()
                .sorted(Comparator.comparing(Libros::getDescargas).reversed())
                .limit(10)
                .forEach(System.out::println);
        System.out.println("**************************");
        System.out.println("Ingrese el nombre del libro a buscar");
        var busqueda = scan.nextLine();
        Optional <Libros> libroSelecto =LibrosObjeto.stream()
                .filter(e->e.getTitulo().toUpperCase().contains(busqueda.toUpperCase()))
                .findFirst();
                if(libroSelecto.isPresent()){
                    System.out.println("**************************");
                    System.out.println("Titulo: " + libroSelecto.get().getTitulo());
                    System.out.println("Autor: " + libroSelecto.get().getAutores());
                    System.out.println("Idiomas: " + libroSelecto.get().getIdiomas());
                    System.out.println("Resumen: " + libroSelecto.get().getResumen());
                    System.out.println("Descargas: " + libroSelecto.get().getDescargas());
                    System.out.println("**************************");
                } else{
                    System.out.println("Libro no encontrado");
                }
        System.out.println("Estadisticas: ");
        DoubleSummaryStatistics est = LibrosObjeto.stream()
                .collect(Collectors.summarizingDouble(Libros::getDescargas));
        System.out.println("Datos revisados: " + est.getCount());
        System.out.println("Suma de descargas: " + est.getSum());
        System.out.println("Minimo de descargas: " + est.getMin());
        System.out.println("Maximo de descargas: " + est.getMax());
        System.out.println("**************************");


    }
}
