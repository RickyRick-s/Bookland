package com.rick.Bookland.Models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Books(@JsonAlias("id") int numero, @JsonAlias("title") String titulo,
                    @JsonAlias("subjects") List<String> tema, @JsonAlias("authors") List<Persons> autores,
                    @JsonAlias("summaries") List<String> resumen,
                    @JsonAlias("languages") List<String> idiomas,
                    @JsonAlias("download_count") Double descargas) {

}
