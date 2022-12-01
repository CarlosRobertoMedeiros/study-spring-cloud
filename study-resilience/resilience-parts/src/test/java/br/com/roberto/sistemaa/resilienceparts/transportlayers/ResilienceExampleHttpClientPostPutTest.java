package br.com.roberto.sistemaa.resilienceparts.transportlayers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class ResilienceExampleHttpClientPostPutTest {
    private final static Integer TIMEOUT = 3;

    @Test
    public void primeiraEstruturaRequestClientResponsePost(){

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("{\n" +
                        "   \"nome\":\"Carlos Roberto\",\n" +
                        "   \"idade\":40\n" +
                        "}"))
                .uri(URI.create("https://postman-echo.com/post"))
                .headers("Accept","application/xml")
                .timeout(Duration.ofSeconds(TIMEOUT))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(TIMEOUT))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpResponse<String> response = null;
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);


        System.out.println("Async");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}