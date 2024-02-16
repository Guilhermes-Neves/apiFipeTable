package br.alura.desafio.fipe.fipeTable.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(@JsonAlias("codigo") String code,
                   @JsonAlias("nome") String description) {
    @Override
    public String toString() {
        return  "Cód: " + code +
                " Descrição: " + description;
    }
}
