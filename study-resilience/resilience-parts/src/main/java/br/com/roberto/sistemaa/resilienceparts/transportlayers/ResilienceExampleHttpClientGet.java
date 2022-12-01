package br.com.roberto.sistemaa.resilienceparts.transportlayers;

import io.github.resilience4j.retry.annotation.Retry;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/resilience")
public class ResilienceExampleHttpClientGet {
    private Logger LOGGER = LoggerFactory.getLogger(ResilienceExampleHttpClientGet.class);

    @GetMapping
    @Retry(name = "default" , fallbackMethod = "mensagemGenerica")
    public ResponseEntity<?> getAll() throws IOException, InterruptedException {

        LOGGER.info("Mensagem Recebida");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8082/clients/client"))
                .header("Accept","application/json")
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println(response.body());
        return ResponseEntity.ok(response.body());

    }

    public ResponseEntity<?> mensagemGenerica(Exception ex){
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }


}
