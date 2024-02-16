package br.alura.desafio.fipe.fipeTable.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(@JsonAlias("Valor") String price,
                      @JsonAlias("Marca") String brandName,
                      @JsonAlias("Modelo") String carModel,
                      @JsonAlias("AnoModelo") Integer yearModel,
                      @JsonAlias("Combustivel") String fuelType
                      ) {
}
