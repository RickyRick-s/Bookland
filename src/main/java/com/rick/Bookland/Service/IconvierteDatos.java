package com.rick.Bookland.Service;

public interface IconvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
