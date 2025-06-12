# 📚 Bookland - Biblioteca Digital con Gutendex API

Bookland es una aplicación de consola desarrollada en **Java** con **Spring Boot** que permite explorar, registrar y consultar libros digitales utilizando la API pública de **Gutendex**. Además, puedes gestionar autores, realizar búsquedas avanzadas por idioma, filtrar por fechas y ver el top de libros más descargados.

## 🚀 Características

- 🔎 **Buscar libros por título** y registrarlos en la base de datos local.
- 📚 **Listar todos los libros** y autores registrados.
- 📆 **Filtrar autores** por rango de años de nacimiento.
- 🌐 **Buscar libros por idioma** (por ejemplo: `en`, `es`, `fr`, etc.).
- 🏆 **Top 5 libros más descargados**.
- 🔗 Integración directa con la [API de Gutendex](https://gutendex.com/).
- 🗃️ **Persistencia** con base de datos relacional (H2, MySQL, etc.).
- 👥 **Gestión de relaciones** muchos a muchos entre libros y autores.

## 🛠️ Tecnologías

- Java 17+
- Spring Boot
- Maven
- JPA/Hibernate
- API REST (Gutendex)
- Base de datos relacional (H2, MySQL, etc.)
