package com.rick.Bookland.Principal;


import com.rick.Bookland.Models.*;
import com.rick.Bookland.Service.ConvierteDatos;
import com.rick.Bookland.Service.UsoAPI;


import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

public class principal {
    private Scanner scan = new Scanner(System.in);
    private UsoAPI API = new UsoAPI();
    private final String URL = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibrosRepository lrepository;
    private AutoresRepository arepository;

    public principal(LibrosRepository lrepository, AutoresRepository arepository) {
        this.lrepository = lrepository;
        this.arepository = arepository;
    }

    public void IniciarUI() {
        var control = -1;
        while (control != 0) {
            var menu = """
                    1.-Buscar libro por Titulo
                    2.-listar libros registrados
                    3.-listar autores registrados
                    4.-listar autores vivos en determinado tiempo
                    5.-libros por idioma
                    6.-Top 5 descargas
                    0.-Salir
                    """;
            System.out.println();
            System.out.println("###########################");
            System.out.println(menu);
            System.out.println("###########################");
            control = Integer.valueOf(scan.nextLine());
            switch (control) {
                case 1:
                    actualizaBasededatos();
                    break;
                case 2:
                    muestraLibros();
                    break;
                case 3:
                    muestraAutores();
                    break;
                case 4:
                    muestraAutoresVivosEntre();
                    break;
                case 5:
                    muestraLibrosPorIdioma();
                    break;
                case 6:
                    topDescargas();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
            }
        }
    }

    public void actualizaBasededatos(){
        String tituloLibro = buscarLibroPorTitulo();
        registraLibroyAutor(tituloLibro);
    }

    public String buscarLibroPorTitulo() {
        System.out.println("Ingrese el titulo del libro");
        var titulo = scan.nextLine();
        return titulo;
    }

    //Solucion a duplicados, normalizar al maximo.
    //buscar alternativas

    public void registraLibroyAutor(String libroTitulo) {
        Libros libro = creaLibro(libroTitulo);
        String tituloReal = normalizar(libro.getTitulo());
        Optional<Libros> libroExistente = lrepository.findByTituloIgnoreCase(tituloReal);
        if (libroExistente.isPresent()) {
            System.out.println("Libro ya registrado:");
            System.out.println(libroExistente.get());
            return;
        }

        List<Autores> autoresGestionados = new ArrayList<>();
        for (Autores autor : libro.getAutores()) {
            String nombreNormalizado = normalizar(autor.getNombre());
            Optional<Autores> autorExistente = arepository.findByNombreIgnoreCase(nombreNormalizado);
            if (autorExistente.isPresent()) {
                autoresGestionados.add(autorExistente.get());
            } else {
                autor.setNombre(nombreNormalizado);
                autoresGestionados.add(arepository.save(autor));
            }
        }
        libro.setAutores(autoresGestionados);
        libro.setTitulo(tituloReal);
        lrepository.save(libro);
        System.out.println("Libro y autor(es) registrados correctamente:");
        System.out.println(libro);
    }

    public Libros creaLibro(String titulo) {
        String nombreCodificado = URLEncoder.encode(titulo, java.nio.charset.StandardCharsets.UTF_8);
        var json = API.obtenerDatos(URL + "?search=" + nombreCodificado);
        var datos = conversor.obtenerDatos(json, Datos.class);
        return datos.books().stream()
                .findFirst()
                .map(Libros::new)
                .orElse(null);
    }

    private String normalizar(String texto) {
        return texto == null ? null : texto.trim().toLowerCase();
    }
    private void muestraLibros(){
        List<Libros> listaLibros = lrepository.findAll();
        listaLibros.forEach(System.out::println);
    }
    private void muestraAutores() {
        List<Autores> listaAutores = arepository.findAll();
        listaAutores.forEach(System.out::println);
    }
    private void muestraAutoresVivosEntre(){
        System.out.println("Ingrese la fecha inicial");
        var fechaI = Integer.valueOf(scan.nextLine());
        System.out.println("Ingrese la fecha final");
        var fechaF = Integer.valueOf(scan.nextLine());
        List<Autores> listaAutoresVivos = arepository.findBynacimientoBetween(fechaI,fechaF);
        listaAutoresVivos.forEach(System.out::println);
    }
    private void muestraLibrosPorIdioma(){
        System.out.println("Inserte el idioma (en, es, fr, pr, fi)");
        var idioma = scan.nextLine();
        List<Libros> libroIdioma = lrepository.findByidiomas(idioma);
        libroIdioma.forEach(System.out::println);
    }
    private void topDescargas(){
        List<Libros> libroDescarga = lrepository.findTop5ByOrderByDescargasDesc();
        libroDescarga.forEach(System.out::println);
    }
}
