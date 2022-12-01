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
import java.util.concurrent.TimeUnit;

class ResilienceExampleHttpClientGetDeleteTest {
    private final static String URL_CLIENT = "http://localhost:8082/clients/client";

    @Test
    public void primeiraEstruturaRequestClientResponse(){

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL_CLIENT))
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
        Assertions.assertEquals(response.statusCode(),200);
    }

    @Test
    public void segundaEstruturaParaDelete(){
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("https://postman-echo.com/delete"))
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
        //System.out.println(response.body());
        Assertions.assertEquals(response.statusCode(),200);
    }

    @Test
    public void terceiraEstruturaRequestClientResponseComHeader(){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://apichallenges.herokuapp.com/todos"))
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
        //System.out.println(response.body());
        Assertions.assertEquals(response.statusCode(),200);
    }

    @Test
    public void quartaEstruturaRequestClientResponseComHeaderSegurandoConexaoComTimeOut (){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://cat-fact.herokuapp.com/facts/"))
                .header("Accept","application/json")
                //.headers("Accept","application/json","chave","valor","chave","valor") Fazer isso n vezes
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.body());
        Assertions.assertEquals(response.statusCode(),200);

    }

    @Test
    @Timeout(value = 3, unit = TimeUnit.SECONDS)
    public void quintaEstruturaRequestClientResponseComHeaderConnectTimeOut(){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://cat-fact.herokuapp.com/facts/"))
                .header("Accept","application/json")
                //.headers("Accept","application/json","chave","valor","chave","valor") Fazer isso n vezes
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3)) //Caso não consiga estabelecer conexão
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.body());
        Assertions.assertEquals(response.statusCode(),200);
    }

    @Test
    public void sextaEstruturaRequestClientResponseComHeaderFollowUrl(){

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.getpostman.com"))
                .header("Accept","application/json")
                //.headers("Accept","application/json","chave","valor","chave","valor") Fazer isso n vezes
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3)) //Caso não consiga estabelecer conexão
                .followRedirects(HttpClient.Redirect.NORMAL) //Pode ser um problema de segurança trocar de https para http
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(response.statusCode());
//        System.out.println(response.headers());
//        System.out.println(response.version());
//        System.out.println(response.body());
        Assertions.assertEquals(response.statusCode(),200);
    }

    @Test
    public void setimaEstruturaRequestClientResponseComHeaderEEnvioAssincrono () throws InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.getpostman.com"))
                .header("Accept","application/json")
                //.headers("Accept","application/json","chave","valor","chave","valor") Fazer isso n vezes
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3)) //Caso não consiga estabelecer conexão
                //.followRedirects(HttpClient.Redirect.NORMAL) //Pode ser um problema de segurança trocar de https para http
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(String::toUpperCase)
                .thenAccept(System.out::println);

        System.out.println("Async");
        TimeUnit.SECONDS.sleep(2);
    }
}