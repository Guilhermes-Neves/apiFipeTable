package br.alura.desafio.fipe.fipeTable.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConsumer {

    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    public static String getData(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (RuntimeException |IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }

    public String getAddress(String vehicleType) {
        if (vehicleType.toLowerCase().contains("carr")) {
            return BASE_URL + "carros/marcas/";
        } else if (vehicleType.toLowerCase().contains("mot")) {
            return BASE_URL + "motos/marcas/";
        } else {
            return BASE_URL + "caminhoes/marcas/";
        }
    }

}
