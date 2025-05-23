package com.rick.Bookland.Models;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Persons(@JsonAlias("birth_year") int nacimiento,
                      @JsonAlias("death_year")int fallecimiento,
                      @JsonAlias("name") String nombre) {
}
