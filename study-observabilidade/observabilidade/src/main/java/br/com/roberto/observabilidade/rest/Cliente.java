package br.com.roberto.observabilidade.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
public class Cliente {

    @GetMapping
    public ResponseEntity<List<ClienteTO>> getAll(){
        List<ClienteTO> clientes = new ArrayList<>();
        clientes = Arrays.asList(new ClienteTO("Carlos","Roberto"),
            new ClienteTO("Luciene","Alves"));
        return ResponseEntity.ok(clientes);
    }
}
