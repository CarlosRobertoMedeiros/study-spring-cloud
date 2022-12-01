package br.com.roberto.sistemaa.resilienceparts.transportlayers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

class ResilienceExampleHttpClientCrud {
    private final static Integer TIMEOUT = 3;

    @Test
    public void consultaServiceClient(){

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8082/clients/client"))
                .header("Accept","application/json")
                //.headers("Accept","application/json","chave","valor","chave","valor") Fazer isso n vezes
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tentativa:");
        System.out.println(response.body());
        Assertions.assertEquals(response.statusCode(),200);
    }


}